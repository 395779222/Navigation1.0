/*
 * 文 件 名:  NavigationServlet.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-5
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.action;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import cn.eainfo.navigation.po.Natest;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.ReleaseNavigationService;




/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class GetNavigationAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplate;
	private String type;
	private String areaNo;
	private String enname;
	private Integer maxNum;
	private Integer locationMax;
	private List<Navigation>listNavigation;
	private List<Shortcuts> shortList;
	private ReleaseNavigationService releaseNavigationService;
	private List<Natest>testlist;
	//广告位图片地址
	private String adIcon;
	/** 
	 * <与预览导航页面>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String getPreLookNavigation(){
		logger.log(Level.INFO, " getPreLookNavigation()开始 BEGIN");
		try {
			Navigation na = new Navigation();
			na.setAreaNo(areaNo);
			na.setType(type);
			listNavigation = sqlMapClientTemplate.queryForList("Navigation.getPreLookList",na);
			if(null!=listNavigation&&listNavigation.size()>0){
				maxNum = listNavigation.get(0).getNum();
				locationMax = listNavigation.get(0).getLocation();
			}
			Shortcuts shortcuts =new Shortcuts();
			shortcuts.setType(type);
			shortcuts.setAreaNo(areaNo);
			if((Shortcuts)sqlMapClientTemplate.queryForObject("Shortcuts.getPreLookShortAdIcon", shortcuts)!=null){
				adIcon = ((Shortcuts)sqlMapClientTemplate.queryForObject("Shortcuts.getPreLookShortAdIcon", shortcuts)).getIcon();
			}
			//获取快捷键列表
			shortList=sqlMapClientTemplate.queryForList("Shortcuts.getPreLookShorts", shortcuts);
			logger.log(Level.INFO, " getNavigation()end");
		} catch (Exception e) {
			logger.log(Level.ERROR, " getNavigation()异常");
			e.printStackTrace();
		}
		return type+"PreLook";
	}
	/**
	 * @param 查询导航信息
	 * @param action
	 * @param request
	 * @param response
	 */ 
	public String getNavigation(){
		logger.log(Level.INFO, " getNavigation()开始 BEGIN");
		try {
			Navigation na = new Navigation();
			//这里把名字当做编号使用
			na.setAreaNo(enname);
			na.setType(type);
			listNavigation = releaseNavigationService.getNavigationRealse(na);
			if(null!=listNavigation&&listNavigation.size()>0){
				maxNum = listNavigation.get(0).getNum();
				locationMax = listNavigation.get(0).getLocation();
			}
			Shortcuts shortcuts =new Shortcuts();
			shortcuts.setType(type);
			shortcuts.setAreaNo(enname);
			//获取快捷键列表
			shortList=sqlMapClientTemplate.queryForList("Shortcuts.getShortByType", shortcuts);
			if((Shortcuts)sqlMapClientTemplate.queryForObject("Shortcuts.getShortAdIcon", shortcuts)!=null){
				adIcon = ((Shortcuts)sqlMapClientTemplate.queryForObject("Shortcuts.getShortAdIcon", shortcuts)).getIcon();
			}
			logger.log(Level.INFO, " getNavigation()end");
			//设置index_city_type.jsp显示的导航列表
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/index"+type+".jsp");
			//dispatcher.forward(request, response);
		} catch (Exception e) {
			logger.log(Level.ERROR, " getNavigation()异常");
			e.printStackTrace();
		}
		return type;
	}
	/**
	 * @param 查询测试信息
	 * @param action
	 * @param request
	 * @param response
	 */
	public String getTest(){
		try {
			testlist=releaseNavigationService.getTest (type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type+"tv";
	}
	/**
	 * @return 返回 sqlMapClientTemplate
	 */
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}
	/**
	 * @param 对sqlMapClientTemplate进行赋值
	 */
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	/**
	 * @return 返回 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param 对type进行赋值
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return 返回 enname
	 */
	public String getEnname() {
		return enname;
	}
	/**
	 * @param 对enname进行赋值
	 */
	public void setEnname(String enname) {
		this.enname = enname;
	}
	/**
	 * @return 返回 maxNum
	 */
	public Integer getMaxNum() {
		return maxNum;
	}
	/**
	 * @param 对maxNum进行赋值
	 */
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	/**
	 * @return 返回 listNavigation
	 */
	public List<Navigation> getListNavigation() {
		return listNavigation;
	}
	/**
	 * @param 对listNavigation进行赋值
	 */
	public void setListNavigation(List<Navigation> listNavigation) {
		this.listNavigation = listNavigation;
	}
	/**
	 * @return 返回 shortList
	 */
	public List<Shortcuts> getShortList() {
		return shortList;
	}
	/**
	 * @param 对shortList进行赋值
	 */
	public void setShortList(List<Shortcuts> shortList) {
		this.shortList = shortList;
	}
	/**
	 * @return 返回 releaseNavigationService
	 */
	public ReleaseNavigationService getReleaseNavigationService() {
		return releaseNavigationService;
	}
	/**
	 * @param 对releaseNavigationService进行赋值
	 */
	public void setReleaseNavigationService(
			ReleaseNavigationService releaseNavigationService) {
		this.releaseNavigationService = releaseNavigationService;
	}
	/**
	 * @return 返回 testlist
	 */
	public List<Natest> getTestlist() {
		return testlist;
	}
	/**
	 * @param 对testlist进行赋值
	 */
	public void setTestlist(List<Natest> testlist) {
		this.testlist = testlist;
	}
	/**
	 * @return 返回 locationMax
	 */
	public Integer getLocationMax() {
		return locationMax;
	}
	/**
	 * @param 对locationMax进行赋值
	 */
	public void setLocationMax(Integer locationMax) {
		this.locationMax = locationMax;
	}
	/**
	 * @return 返回 areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * @param 对areaNo进行赋值
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	/**
	 * @return 返回 adIcon
	 */
	public String getAdIcon() {
		return adIcon;
	}
	/**
	 * @param 对adIcon进行赋值
	 */
	public void setAdIcon(String adIcon) {
		this.adIcon = adIcon;
	}
	
}
