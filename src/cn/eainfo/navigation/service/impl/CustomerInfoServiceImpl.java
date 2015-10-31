/*
 * 文 件 名:  CustomerInfoServiceImpl.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-12
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.navigation.po.CustomerInfo;
import cn.eainfo.navigation.service.CustomerInfoService;
import cn.eainfo.system.po.SysArea;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CustomerInfoServiceImpl implements CustomerInfoService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	/** {@inheritDoc} */
	public String getAreaNoByCardNo(String cradNo) {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo = (CustomerInfo) sqlMapClientTemplate.queryForObject("CustomerInfo.getAreaNoByCardNo",cradNo);
		if(customerInfo!=null){
			return customerInfo.getAreaNo();
		}
		else{
			return "";
		}
	}
	
	/** {@inheritDoc} */
	public void insert(CustomerInfo customerInfo) {
		sqlMapClientTemplate.insert("CustomerInfo.insert",customerInfo);
		
	}
	
	/** {@inheritDoc} */
	public String getUserCityName(String districtCode) {
		String userCityName = "";
		SysArea sysArea = (SysArea) sqlMapClientTemplate.queryForObject("CustomerInfo.getUserCityNameByDistrictCode",districtCode);
		if(sysArea!=null){
			//这里sql查询吧名称转化为areaNo
			userCityName = sysArea.getEnName();
		}
		return userCityName;
	}
	/** {@inheritDoc} */ 
	public String getCityEnnameByAreaNo(String areaNo) {
		SysArea sysArea = (SysArea) sqlMapClientTemplate.queryForObject("SysArea.getCityEnnameByAreaNo",areaNo);
		String enname = sysArea.getEnName();
		return enname;
	}

}
