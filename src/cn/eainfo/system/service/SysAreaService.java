package cn.eainfo.system.service;

import java.util.List;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.AjaxListData;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;

/**
 * 系统地区管理
 */
public interface SysAreaService {
	
	public List getSysAreaList(SysAreaModel sm);
	
	public AjaxJsonListData getSysArea(SysAreaModel sysAreaModel);
	
	public List getSysAreaByPow(SysAreaModel sysAreaModel);
	/**
	 * 根据用户管理地区获取指定地区编号的子地区，同时名称需要设置退格符
	 * 
	 * @param areaNo地区id
	 * @return List地区列表
	 */
	public List getBySeparatorSysArea(String areaNo, String areaValue);

	public List getBySysArea(String areaNo, String areaValue);

	/**
	 * 获取指定地区编号的子地区，同时名称需要设置退格符
	 * 
	 * @param areaNo地区id
	 * @return List地区列表
	 */
	public List getBySeparatorSysArea(String areaNo);

	/**
	 * 根据id获取地区对象
	 * 
	 * @param areaNo
	 *            id
	 * @return SysArea地区对象
	 */
	public SysArea getSysAreaBean(String areaNo);

	/**
	 * 获取所有地区
	 * 
	 * @return List地区对象
	 */
	public List getAll();

	/**
	 * 根据用户id获取对应地区
	 * 
	 * @param userId用户id
	 * 
	 * @return String地区字符串
	 */
	public String getLoginArea(long userId);

	public List getAreaByUserId(long userId);
	
	/**
	 * 获取地区名
	 * @param AreaNo
	 * @return
	 */
	public List getAreaNoAndName(String areaNo);

	public void edit( SysArea area);

	public void insert(SysArea area);

}
