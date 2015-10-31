package cn.eainfo.system.service;

import cn.eainfo.system.po.LoginLog;


/**
 * 用户管理
 */
public interface LoginLogService {

	/**
	 * 新增用户
	 * 
	 * @param sysUser用户对象
	 * @return void
	 */
	public void insertLoginLog(LoginLog loginLog);

}
