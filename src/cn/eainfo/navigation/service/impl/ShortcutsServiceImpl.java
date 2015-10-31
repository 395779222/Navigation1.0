/*
 * 文 件 名:  ShortcutsServiceImpl.java
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
import cn.eainfo.navigation.model.ShortcutsModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.navigation.po.CompanyInfo;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.ShortcutsService;
import cn.eainfo.system.po.SysArea;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShortcutsServiceImpl implements ShortcutsService{
	/** {@inheritDoc} */
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	public AjaxJsonListData getShortPage(ShortcutsModel shortcutsModel) {
		long count = (Long) sqlMapClientTemplate.queryForObject("Shortcuts.getShortcutsCount", shortcutsModel);
		PageBean page = new PageBean(shortcutsModel.getPage(),shortcutsModel.getPageSize(), count);
		shortcutsModel.setStart(page.getStart());
		shortcutsModel.setEnd(page.getEnd());
		// 查询数据
		@SuppressWarnings("rawtypes")
		List<Shortcuts> pageList = sqlMapClientTemplate.queryForList("Shortcuts.getShortcutsRecord", shortcutsModel);
		if(pageList!=null&&pageList.size()>0){
			String cityName="";
			for(Shortcuts shortcuts :pageList){
				cityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",shortcuts.getAreaNo())).getName();
				shortcuts.setCityEnName(cityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	/** {@inheritDoc} */
	 
	public void deleteShort(long id) {
		Shortcuts shortcuts = (Shortcuts) sqlMapClientTemplate.queryForObject("Shortcuts.getBean",id);
		sqlMapClientTemplate.delete("Shortcuts.delete",id);
		//删除后更新地区审核信息表里面快捷键审核的数量
		updateAreaCheckMsg(shortcuts.getAreaNo(),shortcuts.getType(),getShortCheckNum(shortcuts),getShortNotCheckNum(shortcuts));
		
	}
	/** {@inheritDoc} */
	public void add(Shortcuts shortcuts) {
		sqlMapClientTemplate.insert("Shortcuts.insert",shortcuts);
		//新增后更新地区审核信息表里面快捷键审核的数量
		updateAreaCheckMsg(shortcuts.getAreaNo(),shortcuts.getType(),getShortCheckNum(shortcuts),getShortNotCheckNum(shortcuts));
		
		 
	}
	/** {@inheritDoc} */
	 
	public Shortcuts getShortBean(long id) {
		Shortcuts shortcuts = (Shortcuts) sqlMapClientTemplate.queryForObject("Shortcuts.getBean",id);
		return shortcuts;
	}
	/** {@inheritDoc} */
	 
	public void edit(Shortcuts shortcuts) {
		sqlMapClientTemplate.update("Shortcuts.update",shortcuts);
		updateAreaCheckMsg(shortcuts.getAreaNo(),shortcuts.getType(),getShortCheckNum(shortcuts),getShortNotCheckNum(shortcuts));
	}
	/*获取快捷键待审核的数量*/
	private long getShortCheckNum(Shortcuts shortcuts){
		return  (Long) sqlMapClientTemplate.queryForObject("Shortcuts.getCheckNum",shortcuts);
	}
	/*获取快捷键已审核的数量*/
	private long getShortNotCheckNum(Shortcuts shortcuts){
		return  (Long) sqlMapClientTemplate.queryForObject("Shortcuts.getNotCheckNum",shortcuts);
	}
	/** 
	 * <快捷键变更同步更新地区审核信息表里的快捷键审核数量>
	 * @param areaNo
	 * @param type
	 * @param shortCheckNum
	 * @see [类、类#方法、类#成员]
	 */
	private void updateAreaCheckMsg(String areaNo,String type,long shortCheckNum,long shortNotCheckNum){
		AreaNavCheckMsg areaNavCheckMsg = new AreaNavCheckMsg();
		areaNavCheckMsg.setAreaNo(areaNo);
		areaNavCheckMsg.setType(type);
		areaNavCheckMsg.setShortCheckNum(new Integer((int)shortCheckNum));
		areaNavCheckMsg.setShortNotCheckNum(new Integer((int)shortNotCheckNum));
		areaNavCheckMsg.setCheckState("待审核");
		sqlMapClientTemplate.update("AreaNavCheckMsg.updateShortCheckNum",areaNavCheckMsg);
	}
	/**获取城市的高清或标清的快捷键时候已经通过了审核*/
	 
	public String getShortIsHavePssed(Shortcuts shortcuts) {
		long num = (Long) sqlMapClientTemplate.queryForObject("Shortcuts.getNotPassedNum",shortcuts);
		//若没通过审核的数量大于0 则返回0表示改成是快捷键没有通过审核
		if(num>0){
			return "0";
		}
		else{
			return "1";
		}
	}
	public String keyIsExist(Shortcuts shortcuts) {
		long num = (Long)sqlMapClientTemplate.queryForObject("Shortcuts.getKeyIsExist",shortcuts);
		if(num>=1){
			return "1";
		}
		else{
			return "0";
		}
	}

}
