/*
 * 文 件 名:  ReleaseServiceImpl.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.model.ReleaseModel;
import cn.eainfo.navigation.po.Release;
import cn.eainfo.navigation.service.ReleaseService;
import cn.eainfo.system.po.SysArea;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ReleaseServiceImpl implements ReleaseService{

	/** {@inheritDoc} */
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public AjaxJsonListData getReleasePage(ReleaseModel releaseModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("Release.getReleaseCount", releaseModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(releaseModel.getPage(),releaseModel.getPageSize(), count);
		releaseModel.setStart(page.getStart());
		releaseModel.setEnd(page.getEnd());
		// 查询数
		List<Release> pageList = sqlMapClientTemplate.queryForList("Release.getReleaseRecord", releaseModel);
		
		//获取地区的中文名称塞进了地区编号里面方便前台读取
		if(pageList!=null&&pageList.size()>0){
			String tempCityName = "";
			for(Release release :pageList){
			    tempCityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",release.getAreaNo())).getName();
				release.setAreaNo(tempCityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	/** {@inheritDoc} */
	 
	public void deleteRelease(long id) {
		sqlMapClientTemplate.update("Release.delete",id);
		
	}
	/** {@inheritDoc} */
	 
	public void add(Release release) {
		sqlMapClientTemplate.insert("Release.insert",release);
		
	}
	public Release getShortBean(long id) {
		return (Release) sqlMapClientTemplate.queryForObject("Release.getBean",id);
	}
	public void etid(Release release) {
		sqlMapClientTemplate.update("Release.update",release);
	}
	public List getAllRelease(Release release) {
		List list = sqlMapClientTemplate.queryForList("Release.getExportRelease",release);
		return list;
	}
	public void deleteAllRelease() {
		sqlMapClientTemplate.update("Release.deleteAll","");
	}

}
