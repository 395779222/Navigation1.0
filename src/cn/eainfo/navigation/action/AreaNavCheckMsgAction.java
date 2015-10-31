
package cn.eainfo.navigation.action;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.navigation.model.AreaNavCheckMsgModel;
import cn.eainfo.navigation.po.AreaNavCheckMsg;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.AreaNavCheckMsgService;
import cn.eainfo.navigation.service.NavigationService;
import cn.eainfo.navigation.service.ReleaseNavigationService;
import cn.eainfo.navigation.service.ShortcutsService;

/**
 * <每个省的审核信息>
 * @author  Administrator
 * @version  [版本号, 2014-4-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AreaNavCheckMsgAction {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private AreaNavCheckMsgService areaNavCheckMsgService;
	private ReleaseNavigationService releaseNavigationService;
	private ShortcutsService shortcutsService;
	private NavigationService navigationService;
	private String jsonString;
	private String checkState;
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private String areaNo;
	private String type;
	private String checkText;
	private String cityEnName;
	//具体业务审核的类型（ 导航审核，快捷键审核）
	private String checkType;
	//定义俩状态标志标明导航或者快捷键时候已经审核通过0：表示未完全通过审核1：表示已经审核通过了
	private String shortCheckFlag;
	private String navCheckFlag;
	private String state;
	/** 
	 * <审核信息的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList() {
		try{
			logger.log(Level.INFO, "ajaxList()地区审核信息查询开始");
			AreaNavCheckMsgModel areaNavCheckMsg = new AreaNavCheckMsgModel();
			areaNavCheckMsg.setType(type);
			areaNavCheckMsg.setCheckState(checkState);
			areaNavCheckMsg.setPage(page);
			areaNavCheckMsg.setPageSize(rows);
			AjaxJsonListData ajaxJsonListData =  areaNavCheckMsgService.getAreaNavCheckMsgPage(areaNavCheckMsg);
			jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
			logger.log(Level.INFO, "ajaxList()地区审核信息查询结束");
		}catch(Exception e){
			logger.log(Level.ERROR, "ajaxList()地区审核信息查询出错");
			e.printStackTrace();
		}finally{
			return "jsonString";
		}
	}
	/** 
	 * <具体的地区信息审核详细信息初始化>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxCheckListInit(){
		try{
			/*获取快捷键时候已经全部通过审核
			 * */
			if(state!=null&&!state.equals("")){
				state = new String(state.getBytes("iso-8859-1"),"utf-8"); 
			}
			Shortcuts shortcuts = new Shortcuts();
			shortcuts.setAreaNo(areaNo);
			shortcuts.setType(type);
			shortCheckFlag = shortcutsService.getShortIsHavePssed(shortcuts);
			/*获取导航时候已经全部通过审核 
			 * */
			Navigation nav = new Navigation();
			nav.setAreaNo(areaNo);
			nav.setType(type);
			navCheckFlag = navigationService.getNavigationIsHavePssed(nav);
		}catch(Exception e){
		
		}
		return "navigationMsgCheckListInit";
	}
	/** 
	 * <具体的地区信息审核详细信息更新>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxUpdate(){
		logger.log(Level.INFO, "ajaxUpdate()地区审核信息更新开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","地区导航信息审核更新失败","navigation");
		try{
			//一开始就要
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			AreaNavCheckMsg areaCheckMsg = new AreaNavCheckMsg();
			areaCheckMsg.setAreaNo(areaNo);
			areaCheckMsg.setCheckText(checkText);
			areaCheckMsg.setCheckState(checkState);
			areaCheckMsg.setType(type);
			// 如果是在快捷键审核页按下审核按钮则通过此方法
			if(checkType.equals("navigationCheckNum")){
				areaNavCheckMsgService.updateAreaMsgAndNav(areaCheckMsg);
			}
			else{
				areaNavCheckMsgService.updateAreaMsgAndShort(areaCheckMsg);
			}
			ajaxBeanData = new AjaxBeanData("success","地区导航信息审核更新成功","navigation");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			logger.log(Level.INFO, "ajaxUpdate()地区审核信息更新开始");
		}catch(Exception e){
			logger.log(Level.ERROR, "ajaxUpdate()地区审核信息更新出错"+e);
			e.printStackTrace();
		}finally{
			
		}
		return "jsonString";
	}
	/**
	 * @return 返回 areaNavCheckMsgService
	 */
	public AreaNavCheckMsgService getAreaNavCheckMsgService() {
		return areaNavCheckMsgService;
	}

	/**
	 * @param 对areaNavCheckMsgService进行赋值
	 */
	public void setAreaNavCheckMsgService(
			AreaNavCheckMsgService areaNavCheckMsgService) {
		this.areaNavCheckMsgService = areaNavCheckMsgService;
	}

	/**
	 * @return 返回 jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * @param 对jsonString进行赋值
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	/**
	 * @return 返回 checkState
	 */
	public String getCheckState() {
		return checkState;
	}

	/**
	 * @param 对checkState进行赋值
	 */
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}

	/**
	 * @return 返回 rows
	 */
	public long getRows() {
		return rows;
	}

	/**
	 * @param 对rows进行赋值
	 */
	public void setRows(long rows) {
		this.rows = rows;
	}

	/**
	 * @return 返回 page
	 */
	public long getPage() {
		return page;
	}

	/**
	 * @param 对page进行赋值
	 */
	public void setPage(long page) {
		this.page = page;
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
	 * @return 返回 checkText
	 */
	public String getCheckText() {
		return checkText;
	}
	/**
	 * @param 对checkText进行赋值
	 */
	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}
	/**
	 * @return 返回 cityEnName
	 */
	public String getCityEnName() {
		return cityEnName;
	}
	/**
	 * @param 对cityEnName进行赋值
	 */
	public void setCityEnName(String cityEnName) {
		this.cityEnName = cityEnName;
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
	 * @return 返回 checkType
	 */
	public String getCheckType() {
		return checkType;
	}
	/**
	 * @param 对checkType进行赋值
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	/**
	 * @return 返回 shortCheckFlag
	 */
	public String getShortCheckFlag() {
		return shortCheckFlag;
	}
	/**
	 * @param 对shortCheckFlag进行赋值
	 */
	public void setShortCheckFlag(String shortCheckFlag) {
		this.shortCheckFlag = shortCheckFlag;
	}
	/**
	 * @return 返回 navCheckFlag
	 */
	public String getNavCheckFlag() {
		return navCheckFlag;
	}
	/**
	 * @param 对navCheckFlag进行赋值
	 */
	public void setNavCheckFlag(String navCheckFlag) {
		this.navCheckFlag = navCheckFlag;
	}
	/**
	 * @return 返回 shortcutsService
	 */
	public ShortcutsService getShortcutsService() {
		return shortcutsService;
	}
	/**
	 * @param 对shortcutsService进行赋值
	 */
	public void setShortcutsService(ShortcutsService shortcutsService) {
		this.shortcutsService = shortcutsService;
	}
	/**
	 * @return 返回 navigationService
	 */
	public NavigationService getNavigationService() {
		return navigationService;
	}
	/**
	 * @param 对navigationService进行赋值
	 */
	public void setNavigationService(NavigationService navigationService) {
		this.navigationService = navigationService;
	}
	/**
	 * @return 返回 state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param 对state进行赋值
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	
}
