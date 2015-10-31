package cn.eainfo.system.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.AjaxListData;
import cn.eainfo.system.model.SysUserModel;
import cn.eainfo.system.po.SysUser;

/**
 * 用户管理
 */
public interface SysUserService {

	/**
	 * 根据条件分页查询
	 * 
	 * @param sysUserModel查询条件的模型
	 * @return AjaxListData 列表对象
	 */
	public AjaxJsonListData getSysUserPage(SysUserModel sysUserModel);

	/**
	 * 新增用户
	 * 
	 * @param sysUser用户对象
	 * @return void
	 */
	public void insertSysUser(SysUser sysUser);

	/**
	 * 新增用户，并设置用户角色
	 * 
	 * @param sysUser用户对象
	 * @param roleIds角色id
	 * @return void
	 */
	public void insertSysUser(SysUser sysUser, long[] roleIds);

	/**
	 * 修改用户
	 * 
	 * @param sysUser用户对象
	 * @return void
	 */
	public void updateSysUser(SysUser sysUser);

	/**
	 * 修改用户,并修改用户角色
	 * 
	 * @param sysUser用户对象
	 * @param roleIds角色id
	 * @return void
	 */
	public void updateSysUser(SysUser sysUser, long[] roleIds);

	/**
	 * 根据id获取用户对象
	 * 
	 * @param adminId
	 *            id
	 * @return Eiadmin用户对象
	 */
	public SysUser getSysUserBean(long userId);

	/**
	 * 根据角色id获取角色对应的用户
	 * 
	 * @param roleId角色id
	 * 
	 * @return String用户html
	 */
	public String getUserForRole(long roleId);

	/**
	 * 用户登录系统
	 * 
	 * @param loginName登录帐号
	 * @param passWord登录密码
	 * @return SysUser用户
	 */
	public SysUser userLongin(String loginName, String passWord);

	/**
	 * 根据用户id获取选择的角色
	 * 
	 * @param userId用户id
	 * 
	 * @return String用户html
	 */
	public List getRoleByUserId(long userId);

	/**
	 * 根据用户登录帐号查询用户
	 * 
	 * @param loginName用户登录帐号
	 * 
	 * @return List用户列表
	 */
	public List checkUser(String loginName);

}
