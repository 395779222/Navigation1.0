package cn.eainfo.system.action;

import java.util.List;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.system.model.SysRoleModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysMenu;
import cn.eainfo.system.po.SysRole;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysMenuService;
import cn.eainfo.system.service.SysRoleService;
import cn.eainfo.system.service.SysUserService;

/**
 * 角色管理
 */
public class SysRoleAction {
	// 用户管理接口
	private SysRoleService sysRoleService;
	// 菜单管理接口
	private SysMenuService sysMenuService;
	// 用户管理接口
	private SysUserService sysUserService;
	// 地区管理接口
	private SysAreaService sysAreaService;
	/**
	 * 列表查询参数，page当前页码 rows是当前页的行数
	 */
	private long page;// 当前页码
	private long rows;// 当前页条数
	private String jsonString = null;// 返回结果

	// 查询参数
	private String roleNameSelect;// 角色名称
	private int isSystemSelect = 0;// 是否系统管理

	// 新增/修改参数
	private SysRole sysRole;
	private long roleId;// 角色编号
	private String roleName;// 角色名称
	private String roleDesc;// 角色描述
	private int isSystem;// 是否系统管理
	private int status;// 状态 1.正常 2.删除
	private long[] menuIds;// 菜单id
	private long[] userIds;// 用户id
	private String[] areaNos;// 地区id

	private String menuIdsString;// 菜单选择框
	private String userIdsString;// 用户选择框
	private String areaNoString;// 地区选择框

	private long moduleId;// 菜单编号
	private String areaNo;// 地区id
	private int selectStatus;// 选择状态

	/**
	 * 列表查询
	 * 
	 * @throws Exception
	 */
	public String ajaxList() throws Exception {
		SysRoleModel sysRoleModel = new SysRoleModel();
		sysRoleModel.setIsSystem(isSystemSelect);
		sysRoleModel.setRoleName(roleNameSelect);
		sysRoleModel.setStatus(status);
		sysRoleModel.setPage(page);
		sysRoleModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData = sysRoleService
				.getSysRolePage(sysRoleModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}
	/**
	 * 转到列表
	 * 
	 */

	public String forwordList() {
		userIdsString = sysUserService.getUserForRole(1);
		return "forwordList";
	}

	/**
	 * 获取角色
	 * 
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		sysRole = sysRoleService.getSysRoleBean(roleId);
		userIdsString = sysUserService.getUserForRole(roleId);
		menuIdsString = sysMenuService.getMenuForRole(roleId);
		sysRole.setUserIdsString(userIdsString);
		sysRole.setMenuIdsString(menuIdsString);
		jsonString = JSONObjectTool.getObjectJson(sysRole);
		return "jsonString";
	}

	/**
	 * 修改角色
	 * 
	 * @throws Exception
	 */
	public String ajaxSaveOrUpdate() throws Exception {
		
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		if (roleId != 0) {
			// 修改
			sysRole = sysRoleService.getSysRoleBean(roleId);
			sysRole.setIsSystem(isSystem);
			sysRole.setRoleDesc(roleDesc);
			sysRole.setStatus(status);
			if(roleName.equals(sysRole.getRoleName())){
				sysRole.setRoleName(roleName);
				sysRole.setStatus(status);
				sysRoleService.updateSysRole(sysRole);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
				ajaxBeanData.setLabel("sysRole");
			}else{
				List tempList = sysRoleService.findRoleByName(roleName);
				if (tempList == null || tempList.size() == 0) {
					sysRole.setRoleName(roleName);
					sysRole.setStatus(status);
					sysRoleService.updateSysRole(sysRole);
					ajaxBeanData.setResult("sucess");
					ajaxBeanData.setResume("成功");
					ajaxBeanData.setLabel("sysRole");
				}else{
					ajaxBeanData.setResult("faild");
					ajaxBeanData.setResume("当前角色名称：" + roleName + "已经存在！");
					ajaxBeanData.setLabel("sysRole");
				}
			}
			
		} else {
			List tempList = sysRoleService.findRoleByName(roleName);
			if (tempList == null || tempList.size() == 0) {
				// 新增
				sysRole = new SysRole();
				sysRole.setIsSystem(isSystem);
				sysRole.setRoleDesc(roleDesc);
				sysRole.setRoleName(roleName);
				sysRole.setStatus(status);
				sysRoleService.insertSysRole(sysRole);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
				ajaxBeanData.setLabel("sysRole");
			}else{
				ajaxBeanData.setResult("faild");
				ajaxBeanData.setResume("当前角色名称：" + roleName + "已经存在！");
				ajaxBeanData.setLabel("sysRole");
			}
		}
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";

	}

	/**
	 * 显示角色
	 * 
	 * @throws Exception
	 */
	public String ajaxShow() throws Exception {
		sysRole = sysRoleService.getSysRoleBean(roleId);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");
		ajaxBeanData.setData(sysRole);

		jsonString = ajaxBeanData.toString();
		return "jsonString";
	}

	/**
	 * 删除角色
	 * 
	 * @throws Exception
	 */
	public String ajaxDelete() throws Exception {
		sysRole = sysRoleService.getSysRoleBean(roleId);
		sysRole.setStatus(2);
		sysRoleService.updateSysRole(sysRole);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}

	/**
	 * 转到菜单设置
	 */
	public String forwordMenuSet() {
		menuIdsString = sysMenuService.getMenuForRole(roleId);
		return "forwordMenuSet";
	}

	/**
	 * 菜单设置
	 * 
	 * @throws Exception
	 */
	public String ajaxMenuSet() throws Exception {
		sysRoleService.setMenuRel(roleId, menuIds);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");
		jsonString = ajaxBeanData.toString();
		return "jsonString";
	}

	/**
	 * 转到人员设置
	 */
	public String forwordUserSet() {
		userIdsString = sysUserService.getUserForRole(roleId);
		return "forwordUserSet";
	}

	/**
	 * 用户设置
	 * 
	 * @throws Exception
	 */
	public String ajaxUserSet() throws Exception {
		sysRoleService.setUserRel(roleId, userIds);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");
		jsonString = ajaxBeanData.toString();
		return "jsonString";
	}

	/**
	 * 地区选择设置
	 * 
	 * @throws Exception
	 */
	public String ajaxAreaSelect() throws Exception {

		String areaNos = "";
		List areaList = sysAreaService.getBySeparatorSysArea(areaNo);
		int count = areaList.size();
		for (int i = 0; i < count; i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			if (i == 0) {
				areaNos = areaNos + ",";
			}
			areaNos = areaNos + sysArea.getAreaNo() + ",";
		}
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setData(areaNos);
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");

		jsonString = ajaxBeanData.toString();
		return "jsonString";

	}

	/**
	 * 菜单选择设置
	 * 
	 * @throws Exception
	 */
	public String ajaxMenuSelect() throws Exception {
		String menuIds = "";
		List menuList = null;
		if (selectStatus == 1) {
			// 获取子菜单
			menuList = sysMenuService.getBySeparatorSysMenu(moduleId);
			// 获取父菜单
			menuList = sysMenuService.getFartherMenu(moduleId, menuList);
			int count = menuList.size();
			for (int i = 0; i < count; i++) {
				SysMenu sysMenu = (SysMenu) menuList.get(i);
				if (i == 0) {
					menuIds = menuIds + ",";
				}
				menuIds = menuIds + sysMenu.getModuleId() + ",";
			}
		} else {
			menuList = sysMenuService.getBySeparatorSysMenu(moduleId);
			int count = menuList.size();
			for (int i = 0; i < count; i++) {
				SysMenu sysMenu = (SysMenu) menuList.get(i);
				if (i == 0) {
					menuIds = menuIds + ",";
				}
				menuIds = menuIds + sysMenu.getModuleId() + ",";
			}
		}
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setData(menuIds);
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysRole");
		jsonString = ajaxBeanData.toString();
		return "jsonString";
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public String getRoleNameSelect() {
		return roleNameSelect;
	}

	public void setRoleNameSelect(String roleNameSelect) {
		this.roleNameSelect = roleNameSelect;
	}

	public int getIsSystemSelect() {
		return isSystemSelect;
	}

	public void setIsSystemSelect(int isSystemSelect) {
		this.isSystemSelect = isSystemSelect;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public int getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(int isSystem) {
		this.isSystem = isSystem;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public String getMenuIdsString() {
		return menuIdsString;
	}

	public void setMenuIdsString(String menuIdsString) {
		this.menuIdsString = menuIdsString;
	}

	public long[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(long[] menuIds) {
		this.menuIds = menuIds;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public String getUserIdsString() {
		return userIdsString;
	}

	public void setUserIdsString(String userIdsString) {
		this.userIdsString = userIdsString;
	}

	public long[] getUserIds() {
		return userIds;
	}

	public void setUserIds(long[] userIds) {
		this.userIds = userIds;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public int getSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(int selectStatus) {
		this.selectStatus = selectStatus;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public String getAreaNoString() {
		return areaNoString;
	}

	public void setAreaNoString(String areaNoString) {
		this.areaNoString = areaNoString;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String[] getAreaNos() {
		return areaNos;
	}

	public void setAreaNos(String[] areaNos) {
		this.areaNos = areaNos;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}
