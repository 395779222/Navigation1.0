/*
 * 文 件 名:  CompayInfoService.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-12
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.navigation.model.CompanyInfoModel;
import cn.eainfo.navigation.po.CompanyInfo;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CompanyInfoService {

	/** 
	 * <功能详细描述>
	 * @param companyInfoModel
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	AjaxJsonListData getCompanyInfoPage(CompanyInfoModel companyInfoModel);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	void deleteCompanyInfo(long id);

	/** 
	 * <功能详细描述>
	 * @param companyInfo
	 * @see [类、类#方法、类#成员]
	 */
	void update(CompanyInfo companyInfo);

	/** 
	 * <功能详细描述>
	 * @param companyInfo
	 * @see [类、类#方法、类#成员]
	 */
	void insert(CompanyInfo companyInfo);

	/** 
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	CompanyInfo ajaxGetBean(long id);

	

}
