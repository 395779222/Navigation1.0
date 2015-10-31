/*
 * 文 件 名:  CompanyInfoServiceImpl.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-12
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.model.CompanyInfoModel;
import cn.eainfo.navigation.po.CompanyInfo;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.service.CompanyInfoService;
import cn.eainfo.system.po.SysArea;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CompanyInfoServiceImpl implements CompanyInfoService{
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	/** {@inheritDoc} */
	 
	public AjaxJsonListData getCompanyInfoPage(CompanyInfoModel companyInfoModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("CompanyInfo.getCompanyInfoCount", companyInfoModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(companyInfoModel.getPage(),companyInfoModel.getPageSize(), count);
		companyInfoModel.setStart(page.getStart());
		companyInfoModel.setEnd(page.getEnd());
		// 查询数据
		@SuppressWarnings("rawtypes")
		List<CompanyInfo> pageList = sqlMapClientTemplate.queryForList("CompanyInfo.getCompanyInfoRecord", companyInfoModel);
		if(pageList!=null&&pageList.size()>0){
			String cityName="";
			for(CompanyInfo com :pageList){
				cityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",com.getAreaNo())).getName();
				com.setCityEnName(cityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;

	}
	/** {@inheritDoc} */
	 
	public void deleteCompanyInfo(long id) {
		sqlMapClientTemplate.delete("CompanyInfo.delete", id);
		
	}
	/** {@inheritDoc} */
	 
	public void update(CompanyInfo companyInfo) {
		sqlMapClientTemplate.update("CompanyInfo.update", companyInfo);	
	}
	/** {@inheritDoc} */
	 
	public void insert(CompanyInfo companyInfo) {
		sqlMapClientTemplate.insert("CompanyInfo.insert", companyInfo);	
		
	}
	/** {@inheritDoc} */
	 
	public CompanyInfo ajaxGetBean(long id) {
		CompanyInfo companyInfo = (CompanyInfo) sqlMapClientTemplate.queryForObject("CompanyInfo.getBean",id);
		return companyInfo;
	}
	/** {@inheritDoc} */
	 
	

}
