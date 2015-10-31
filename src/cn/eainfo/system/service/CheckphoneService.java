package cn.eainfo.system.service;

import cn.eainfo.system.model.CheckphoneModel;
import cn.eainfo.system.po.Checkphone;
import cn.eainfo.system.po.SysUser;


/**
 * 管理
 */
public interface CheckphoneService {

	/**
	 * 新增
	 * 
	 * @param 
	 * @param 
	 * @return void
	 */
	public void insertCheckphone(Checkphone checkphone);

	/**
	 * 修改
	 * 
	 * @param 
	 * @return void
	 */
	public void updateCheckphone(Checkphone checkphone);
	/**
	 * 修改
	 * 
	 * @param 
	 * @return void
	 */
	public void updateCheckphonebyAccount(String accountid,Long crrstate);

	/**
	 * 根据id获取用户对象
	 * 
	 * @param updateCheckphonebyAccount
	 * @return Eiadmin用户对象
	 */
	public Checkphone getCheckphoneBean(CheckphoneModel checkphoneModel);
}
