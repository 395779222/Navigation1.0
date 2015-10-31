/*
 * 文 件 名:  CustomerInfoService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-12
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import cn.eainfo.navigation.po.CustomerInfo;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CustomerInfoService {

	/** 
	 * <功能详细描述>
	 * @param cradNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	String getAreaNoByCardNo(String cradNo);

	/** 
	 * <功能详细描述>
	 * @param customerInfo
	 * @see [类、类#方法、类#成员]
	 */
	void insert(CustomerInfo customerInfo);

	/** 
	 * <功能详细描述>
	 * @param areaNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	String getUserCityName(String districtCode);

	/** 
	 * <功能详细描述>
	 * @param areaNo
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	String getCityEnnameByAreaNo(String areaNo);

}
