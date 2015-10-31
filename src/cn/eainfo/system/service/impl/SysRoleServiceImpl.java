package cn.eainfo.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.AjaxListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.system.model.SysRoleModel;
import cn.eainfo.system.po.SysRole;
import cn.eainfo.system.po.SysRoleMenuRel;
import cn.eainfo.system.po.SysUserRoleRel;
import cn.eainfo.system.service.SysMaxIdService;
import cn.eainfo.system.service.SysRoleService;

public class SysRoleServiceImpl implements SysRoleService {
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

	public SysRole getSysRoleBean(long roleId) {
		return (SysRole) sqlMapClientTemplate.queryForObject("SysRole.getBean",
				roleId);
	}

	public AjaxJsonListData getSysRolePage(SysRoleModel sysRoleModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject(
				"SysRole.getSysRoleCount", sysRoleModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(sysRoleModel.getPage(),
				sysRoleModel.getPageSize(), count);
		sysRoleModel.setStart(page.getStart());
		sysRoleModel.setEnd(page.getEnd());
		// 查询数据
		List pageList = sqlMapClientTemplate.queryForList(
				"SysRole.getSysRoleRecord", sysRoleModel);
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}

	public List findRoleByName(String roleName){
		return sqlMapClientTemplate
				.queryForList("SysRole.checkRole", roleName);
	}
	public void insertSysRole(SysRole sysRole) {
		sysRole.setRoleId(sysMaxIdService.getSysMaxId("SysRole_roleId"));
		sqlMapClientTemplate.insert("SysRole.insert", sysRole);

	}

	public void updateSysRole(SysRole sysRole) {
		sqlMapClientTemplate.update("SysRole.update", sysRole);

	}

	public void setMenuRel(long roleId, long[] menuIds) {
		if (menuIds != null && menuIds.length > 0) {
			sqlMapClientTemplate
					.delete("SysRoleMenuRel.deleteByRoleId", roleId);// 删除原有设置
			int count = menuIds.length;
			for (int i = 0; i < count; i++) {
				SysRoleMenuRel sysRoleMenuRel = new SysRoleMenuRel();
				sysRoleMenuRel.setMenuId(menuIds[i]);
				sysRoleMenuRel.setRoleId(roleId);
				sqlMapClientTemplate.insert("SysRoleMenuRel.insert",
						sysRoleMenuRel);
			}
		}
	}

	public void setUserRel(long roleId, long[] userIds) {
		if (userIds != null && userIds.length > 0) {
			sqlMapClientTemplate
					.delete("SysUserRoleRel.deleteByRoleId", roleId);// 删除原有设置
			int count = userIds.length;
			for (int i = 0; i < count; i++) {
				SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setUserId(userIds[i]);
				sysUserRoleRel.setRoleId(roleId);
				sqlMapClientTemplate.insert("SysUserRoleRel.insert",
						sysUserRoleRel);
			}
		}
	}

	public List getAll() {
		return sqlMapClientTemplate.queryForList("SysRole.getAll");
	}

}
