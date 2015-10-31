package cn.eainfo.system.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.system.model.SysRoleModel;
import cn.eainfo.system.po.SysRole;

/**
 * 系统角色管理
 * 
 */
public interface SysRoleService {
	/**
	 * 根据条件分页查询
	 * 
	 * @param sysRoleModel查询条件的模型
	 * @return AjaxListData 列表对象
	 */
	public AjaxJsonListData getSysRolePage(SysRoleModel sysRoleModel);
	public List findRoleByName(String roleName);
	/**
	 * 新增角色
	 * 
	 * @param sysRole角色对象
	 * @return void
	 */
	public void insertSysRole(SysRole sysRole);

	/**
	 * 修改角色
	 * 
	 * @param sysUser角色对象
	 * @return void
	 */
	public void updateSysRole(SysRole sysRole);

	/**
	 * 根据id获取角色对象
	 * 
	 * @param roleId
	 *            id
	 * @return SysRole角色对象
	 */
	public SysRole getSysRoleBean(long roleId);

	/**
	 * 设置角色对象的菜单
	 * 
	 * @param roleId角色id
	 * @return menuIds菜单id
	 */
	public void setMenuRel(long roleId, long[] menuIds);

	/**
	 * 设置角色对象的用户
	 * 
	 * @param roleId角色id
	 * @return userIds用户id
	 */
	public void setUserRel(long roleId, long[] userIds);

	/**
	 * 获取所有角色
	 * 
	 * @return List角色列表
	 */
	public List getAll();
}
