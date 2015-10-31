package cn.eainfo.system.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.system.model.SysMenuModel;
import cn.eainfo.system.po.SysMenu;
import cn.eainfo.system.po.SysRoleMenuRel;
import cn.eainfo.system.service.SysMaxIdService;
import cn.eainfo.system.service.SysMenuService;

public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	@Autowired
	private SysMaxIdService sysMaxIdService;

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(
			SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	public SysMaxIdService getSysMaxIdService() {
		return sysMaxIdService;
	}

	public void setSysMaxIdService(SysMaxIdService sysMaxIdService) {
		this.sysMaxIdService = sysMaxIdService;
	}
	public SysMenu getSysMenubyName(String name){
		return (SysMenu) sqlMapClientTemplate.queryForObject("SysMenu.getBeanbyMenuName",name);
	}
	public List getBySeparatorSysMenu(long menuId) {
		List menuAll = sqlMapClientTemplate.queryForList("SysMenu.getAll");
		// 获取最上层部门
		List listTop = getMenuChild(menuId, menuAll);
		return  getSeparatorChild(menuAll, listTop, "");
	}

	/**
	 * 获取带退格符的菜单列表
	 * 
	 * @param menuAll所有菜单
	 * @param topList顶层菜单
	 * @param separator分割符
	 * @return List菜单列表
	 */
	private List getSeparatorChild(List menuAll, List topList, String separator) {
		List backList = new ArrayList();
		separator = separator + "---";// 增加退格
		int count = topList.size();
		if (count > 0) {
			Iterator iter = topList.iterator();
			while (iter.hasNext()) {
				SysMenu sysMenu = (SysMenu) iter.next();
				long moduleId = sysMenu.getModuleId();
				String moduleName = sysMenu.getModuleName();
				moduleName = separator + moduleName;// 设置菜单名称退格
				sysMenu.setModuleName(moduleName);
				backList.add(sysMenu);

				List pChild = getMenuChild(moduleId, menuAll);
				if (pChild.size() > 0) {
					List childtemp = getSeparatorChild(menuAll, pChild,
							separator);
					int childtempCount = childtemp.size();
					for (int i = 0; i < childtempCount; i++) {
						SysMenu sysMenuTemp = (SysMenu) childtemp.get(i);
						backList.add(sysMenuTemp);
					}
				}
			}
		}
		return backList;
	}

	/**
	 * 获取当前菜单下的直接子菜单
	 */
	public List getMenuChild(long moduleId, List menuAll) {
		List menuChildList = new ArrayList();
		if (menuAll != null && menuAll.size() > 0) {
			int count = menuAll.size();
			for (int i = 0; i < count; i++) {
				SysMenu sysMenu = (SysMenu) menuAll.get(i);
				long moduleParentId = sysMenu.getModuleParentId();
				if (moduleId == moduleParentId) {
					menuChildList.add(sysMenu);
				}
			}

		}
		return menuChildList;
	}

	public void deleteSysMenu(long moduleId) {
		sqlMapClientTemplate.delete("SysMenu.delete", moduleId);

	}

	public SysMenu getSysMenuBean(long moduleId) {
		return (SysMenu) sqlMapClientTemplate.queryForObject("SysMenu.getBean",
				moduleId);
	}

	public void insertSysMenu(SysMenu sysMenu) {
		sysMenu.setModuleId(sysMaxIdService.getSysMaxId("SysMenu_moduleId"));
		sqlMapClientTemplate.insert("SysMenu.insert", sysMenu);
	}

	public void updateSysMenu(SysMenu sysMenu) {
		sqlMapClientTemplate.update("SysMenu.update", sysMenu);

	}

	public String getMenuForRole(long roleId) {
		String sysRoleMenuRelString = "";
		List sysRoleMenuRelList = sqlMapClientTemplate.queryForList(
				"SysRoleMenuRel.selectByRoleId", roleId);
		int sysRoleMenuRelCount = sysRoleMenuRelList.size();
		for (int i = 0; i < sysRoleMenuRelCount; i++) {
			if (i == 0) {
				sysRoleMenuRelString = sysRoleMenuRelString + ",";
			}
			SysRoleMenuRel sysRoleMenuRel = (SysRoleMenuRel) sysRoleMenuRelList
					.get(i);
			sysRoleMenuRelString = sysRoleMenuRelString
					+ sysRoleMenuRel.getMenuId() + ",";
		}
		List menuAll = sqlMapClientTemplate.queryForList("SysMenu.getAll");
		// 获取最上层部门
		List listTop = getMenuChild(0, menuAll);
		List backList = getSeparatorChildForRole(sysRoleMenuRelString, menuAll,
				listTop, "");
		String roleIds = "";
		int backSize = backList.size();
		for (int i = 0; i < backSize; i++) {
			SysMenu sysMenuTemp = (SysMenu) backList.get(i);
			roleIds = roleIds + sysMenuTemp.getModuleName();
		}
		return roleIds;
	}

	/**
	 * 获取带选择框的菜单列表
	 * 
	 * @param menuAll所有菜单
	 * @param topList顶层菜单
	 * @param separator分割符
	 * @return List菜单列表
	 */
	private List getSeparatorChildForRole(String seclectMenuId, List menuAll,
			List topList, String separator) {
		List backList = new ArrayList();
		separator = separator + "&nbsp;&nbsp;&nbsp;&nbsp;";// 增加退格
		int count = topList.size();
		if (count > 0) {
			Iterator iter = topList.iterator();
			while (iter.hasNext()) {
				SysMenu sysMenu = (SysMenu) iter.next();
				long moduleId = sysMenu.getModuleId();
				String moduleName = sysMenu.getModuleName();
				List pChild = getMenuChild(moduleId, menuAll);
				//获取菜单的所有子菜单数量
				long childNum = (Long) sqlMapClientTemplate.queryForObject("SysMenu.getChildMenuNum",moduleId);
				if (seclectMenuId.indexOf("," + moduleId + ",") > -1) {
					moduleName = "<div style='height:25px'>" + separator
							+ "<input type='checkbox' name='menuIds' value='"
							+ moduleId
							+ "' checked='checked' onclick='selectMenu(this,"+childNum+")' value='"
							+ moduleId + "'>"
							+ moduleName + "</div>";// 设置菜单选择框
				} else {
					moduleName = "<div style='height:25px'>"
							+ separator
							+ "<input type='checkbox' name='menuIds'  onclick='selectMenu(this,"+childNum+")' value='"
							+ moduleId + "'>" + moduleName + "</div>";// 设置菜单选择框
				}
				//
				sysMenu.setModuleName(moduleName);
				backList.add(sysMenu);
				if (pChild.size() > 0) {
					List childtemp = getSeparatorChildForRole(seclectMenuId,
							menuAll, pChild, separator);
					int childtempCount = childtemp.size();
					for (int i = 0; i < childtempCount; i++) {
						SysMenu sysMenuTemp = (SysMenu) childtemp.get(i);
						backList.add(sysMenuTemp);
					}
				}
			}
		}
		return backList;
	}

	public List getMenuChildByUserId(SysMenuModel sysMenuModel) {
		return sqlMapClientTemplate.queryForList("SysMenu.getMenuChildByUserId", sysMenuModel);
	}

	public List getFartherMenu(long menuId, List menuFarther) {
		SysMenu sysMenu = (SysMenu) sqlMapClientTemplate.queryForObject(
				"SysMenu.getBean", menuId);
		if (sysMenu != null) {
			menuFarther.add(sysMenu);
			long moduleParentId = sysMenu.getModuleParentId();// 父级菜单编号
			if (moduleParentId != 0) {
				getFartherMenu(moduleParentId, menuFarther);
			}
		}
		return menuFarther;
	}

	public List getByModuleParentId(long moduleParentId) {
		return sqlMapClientTemplate.queryForList("SysMenu.getByModuleParentId",
				moduleParentId);
	}

	public List getMenuByUser(long roleId) {
		// TODO Auto-generated method stub
		List sysRoleMenuRelList = sqlMapClientTemplate.queryForList(
				"SysRoleMenuRel.selectByRoleId", roleId);
		return sysRoleMenuRelList;
	}

}
