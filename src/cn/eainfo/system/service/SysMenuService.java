package cn.eainfo.system.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.system.model.SysMenuModel;
import cn.eainfo.system.po.SysMenu;

/**
 * 菜单管理
 */
public interface SysMenuService {
	/**
	 * 获取所有系统菜单，同时名称需要设置退格符
	 * 
	 * @param menuId菜单id
	 * @return List菜单列表
	 */
	public List getBySeparatorSysMenu(long menuId);

	/**
	 * 新增菜单
	 * 
	 * @param sysMenu菜单对象
	 * @return void
	 */
	public void insertSysMenu(SysMenu sysMenu);

	/**
	 * 修改菜单
	 * 
	 * @param sysMenu修改菜单
	 * @return void
	 */
	public void updateSysMenu(SysMenu sysMenu);

	/**
	 * 根据id获取菜单对象
	 * 
	 * @param moduleId
	 *            id
	 * @return SysMenu菜单对象
	 */
	public SysMenu getSysMenuBean(long moduleId);
	/**
	 * 根据id获取菜单对象
	 * 
	 * @param moduleId
	 *            id
	 * @return SysMenu菜单对象
	 */
	public SysMenu getSysMenubyName(String name);

	/**
	 * 根据id删除菜单对象
	 * 
	 * @param moduleId
	 *            id
	 * @return void
	 */
	public void deleteSysMenu(long moduleId);

	/**
	 * 根据角色id获取角色对应菜单
	 * 
	 * @param roleId角色id
	 * 
	 * @return String菜单html
	 */
	public String getMenuForRole(long roleId);

	/**
	 * (登录部分)获取用户登录当前菜单的子菜单
	 */
	public List getMenuChildByUserId(SysMenuModel sysMenuModel);

	/**
	 * 获取当前菜单的所有父菜单
	 * 
	 * @param menuId菜单id
	 * 
	 * @return List父菜单集合
	 */
	public List getFartherMenu(long menuId, List menuFarther);

	public List getByModuleParentId(long moduleParentId);
	
	public List getMenuByUser(long roleId);

}
