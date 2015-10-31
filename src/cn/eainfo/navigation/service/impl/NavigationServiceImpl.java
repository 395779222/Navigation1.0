/*
 * 文 件 名:  NavigationServiceImpl.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-1
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.PageBean;
import cn.eainfo.navigation.model.NavigationModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.service.NavigationService;
import cn.eainfo.system.po.SysArea;


/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NavigationServiceImpl implements NavigationService{

	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	public AjaxJsonListData getNavigationPage(NavigationModel navigationrModel) {
		// 获取总记录数
		long count = (Long) sqlMapClientTemplate.queryForObject("Navigation.getNavigationCount", navigationrModel);
		// 初始化分页对象，获取记录开始和结束行
		PageBean page = new PageBean(navigationrModel.getPage(),navigationrModel.getPageSize(), count);
		navigationrModel.setStart(page.getStart());
		navigationrModel.setEnd(page.getEnd());
		// 查询数据
		@SuppressWarnings("rawtypes")
		List<Navigation> pageList = sqlMapClientTemplate.queryForList("Navigation.getNavigationRecord", navigationrModel);
		String cityName ="";
		if(pageList!=null&&pageList.size()>0){
			for(Navigation nav :pageList){
				cityName = ((SysArea)sqlMapClientTemplate.queryForObject("SysArea.getBeanByAreaNo",nav.getAreaNo())).getName();
				nav.setCityEnName(cityName);
			}
		}
		// 初始化ajax分页，获取ajax对象
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(count);
		ajaxJsonListData.setRows(pageList);
		return ajaxJsonListData;
	}
	/** 通过ID获取导航信息 */
	public Navigation getNavigationBean(long id) {
		Navigation navigation = (Navigation) sqlMapClientTemplate.queryForObject(
				"Navigation.getBean", id);
		return navigation;
	}

	public void updateNavigation(Navigation navigation) {
		//主键
		long id = navigation.getId();
		//获取数据库里面该导航的信息（位置）
		Navigation na = (Navigation)sqlMapClientTemplate.queryForObject("Navigation.getBean",id);
		//原先的位置
		Integer location = na.getLocation();
		//将要修改的位置
		Integer editLocation = navigation.getLocation();
		//若俩此位置相等则说明位置没有修改
		if(location==editLocation){
			sqlMapClientTemplate.update("Navigation.update", navigation);
		}
		//若不相等
		else{
			//建立临时的导航对象，因为要用到俩个位置参数，暂且把将要修改的位置塞到id里面
			Navigation tempNavigation = new Navigation();
			tempNavigation.setAreaNo(navigation.getAreaNo());
			tempNavigation.setType(navigation.getType());
			tempNavigation.setId(editLocation);
			tempNavigation.setLocation(location);
			tempNavigation.setUpdateTime(navigation.getUpdateTime());
			//如果原先的位置大于修改的位置即相当于（原来位置是10想在调整到1那么1-9的原来位置的导航的位置必须+1）
			if(location>editLocation){
				//+1操作所在页面出错就是相当于增加导航出错
				sqlMapClientTemplate.update("Navigation.afterEditUpdate", tempNavigation);
				sqlMapClientTemplate.update("Navigation.update",navigation);
				@SuppressWarnings("unchecked")
				List<Navigation> list = sqlMapClientTemplate.queryForList("Navigation.getUpdateIsAbleError",navigation);
				if(null!=list&&list.size()>0){
					for(int i=0;i<list.size();i++){
						/*当前位置location:(num-1)*15+1<=location<=num*15*/
						if(list.get(i).getLocation()<(15*(list.get(i).getNum()-1)+1)||list.get(i).getLocation()>15*list.get(i).getNum()){
							long updateID = list.get(i).getId();
							sqlMapClientTemplate.update("Navigation.afterAddIsAbleErrorUpdate",updateID);
						}
					}
				}
			}
			else{
				//若是由小变大那么（由1变10那么2-10原来位置的导航必须-1）
				sqlMapClientTemplate.update("Navigation.afterEditUpdate2", tempNavigation);
				sqlMapClientTemplate.update("Navigation.update",navigation);
				@SuppressWarnings("unchecked")
				List<Navigation> list2 = sqlMapClientTemplate.queryForList("Navigation.getUpdateIsAbleError",navigation);
				if(null!=list2&&list2.size()>0){
					/*当前位置location:(num-1)*15+1<=location<=num*15*/
					for(int i=0;i<list2.size();i++){
						if(list2.get(i).getLocation()<(15*(list2.get(i).getNum()-1)+1)||list2.get(i).getLocation()>15*list2.get(i).getNum()){
							long updateID = list2.get(i).getId();
							sqlMapClientTemplate.update("Navigation.afterDeleteIsAbleErrorUpdate",updateID);
						}
					}
				}
			}
			
		}
		updateToAreaCheckMsg(navigation);
	}
	 
	/** 
	 * <当导航信息增删该的时候必须同步更新到地区导航信息设和表里面>
	 * @param navigation
	 * @see [类、类#方法、类#成员]
	 */
	private void updateToAreaCheckMsg(Navigation navigation) {
		/*isToCheckNum = 总数量-审核通过的数量
		 * isNotToCheckNum = 设和通过的数量
		 * */
		long isToCheckNum = (Long)sqlMapClientTemplate.queryForObject("Navigation.getToCheckNum",navigation);
		long isNotToCheckNum = (Long)sqlMapClientTemplate.queryForObject("Navigation.getNotToCheckNum",navigation);
		AreaNavCheckMsg areaCheckMsg = new AreaNavCheckMsg();
		areaCheckMsg.setAreaNo(navigation.getAreaNo());
		areaCheckMsg.setType(navigation.getType());
		areaCheckMsg.setIsNotToCheckNum((int)isNotToCheckNum);
		areaCheckMsg.setIsToCheckNum((int)isToCheckNum);
		areaCheckMsg.setCheckState("待审核");
		sqlMapClientTemplate.update("AreaNavCheckMsg.update",areaCheckMsg); 
		
	}
	/** 删除数据 */
	public void deleteNavigation(long id,Navigation navigation) {
		//删除导航
		sqlMapClientTemplate.delete("Navigation.delete", id);
		//更新它导航的所在位置
		sqlMapClientTemplate.update("Navigation.afterDeleteUpdate", navigation);
		
		@SuppressWarnings("unchecked")
		List<Navigation> list = sqlMapClientTemplate.queryForList("Navigation.getUpdateIsAbleError",navigation);
		//list里面取出来的都是15的整数倍
		if(null!=list&&list.size()>0){
			for(int i=0;i<list.size();i++){
				/*当前位置location:(num-1)*15+1<=location<=num*15*/
				if(list.get(i).getLocation()<(15*(list.get(i).getNum()-1)+1)||list.get(i).getLocation()>15*list.get(i).getNum()){
					long updateID = list.get(i).getId();
					sqlMapClientTemplate.update("Navigation.afterDeleteIsAbleErrorUpdate",updateID);
				}
			}
		}
		updateToAreaCheckMsg(navigation);
	}
	
	/** 增加导航信息 */
	public void addNavigation(Navigation navigation) {
		Navigation tempNavigation = (Navigation) sqlMapClientTemplate.queryForObject(
				"Navigation.getBeanByLocation", navigation);
		if(tempNavigation!=null){
			//如果插入的所在位置已经有导航占用那么将此位置以及该地区导航位置大于该位置的导航的所在位置+1
			sqlMapClientTemplate.update("Navigation.afterAddUpdate",navigation);
			
			@SuppressWarnings("unchecked")
			List<Navigation> list = sqlMapClientTemplate.queryForList("Navigation.getUpdateIsAbleError",navigation);
			if(null!=list&&list.size()>0){
				for(int i=0;i<list.size();i++){
					if(list.get(i).getLocation()<(15*(list.get(i).getNum()-1)+1)||list.get(i).getLocation()>15*list.get(i).getNum()){
						long updateID = list.get(i).getId();
						sqlMapClientTemplate.update("Navigation.afterAddIsAbleErrorUpdate",updateID);
					}
				}
			}
		}
		sqlMapClientTemplate.insert("Navigation.insert", navigation);	
		updateToAreaCheckMsg(navigation);
	}
	
	/** 获取导出列表 */
	@SuppressWarnings("rawtypes")
	public List getExportList(NavigationModel navigationModel) {
		List list = sqlMapClientTemplate.queryForList("Navigation.getExportNavigation", navigationModel);
		return list;
	}
	
	/** 导入excel删除所属地区导航信息 */
	public boolean deleteNavigationListByAreaNo( Navigation navigation) {
		sqlMapClientTemplate.delete("Navigation.deleteNavigationListByAreaNo",navigation);
		//同步更新地区信息表
		long navigationPassNum = (Long)sqlMapClientTemplate.queryForObject("Navigation.getToCheckNum",navigation);
		long navigationNotPassNum = (Long)sqlMapClientTemplate.queryForObject("Navigation.getNotToCheckNum",navigation);
		AreaNavCheckMsg areaCheckMsg = new AreaNavCheckMsg();
		areaCheckMsg.setAreaNo(navigation.getAreaNo());
		areaCheckMsg.setType(navigation.getType());
		areaCheckMsg.setIsToCheckNum((int) navigationNotPassNum);
		areaCheckMsg.setIsNotToCheckNum((int) navigationPassNum);
		sqlMapClientTemplate.update("AreaNavCheckMsg.updateShortCheckNum",areaCheckMsg); 
		return true;
	}
	/*根据导航所属地区及其类型来获取满足条件最大的位置的值*/
	public Integer getMaxLoactionByAreaAndType(Navigation navigation) {
		@SuppressWarnings("unchecked")
		Integer maxNum =0;
		List<Navigation>list = sqlMapClientTemplate.queryForList("Navigation.getMaxLoactionByAreaAndType",navigation);
		if(list!=null&&list.size()>0){
			maxNum = list.get(0).getLocation();
		}
		return maxNum;
	}
	public String getNavigationIsHavePssed(Navigation nav) {
		long num = (Long) sqlMapClientTemplate.queryForObject("Navigation.getNotPassedNum",nav);
		//若没通过审核的数量大于0 则返回0表示改成是快捷键没有通过审核
		if(num>0){
			return "0";
		}
		else{
			return "1";
		}
	}	
}
