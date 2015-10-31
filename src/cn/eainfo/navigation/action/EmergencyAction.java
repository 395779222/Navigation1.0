package cn.eainfo.navigation.action;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.common.excel.CommonDateUtils;
import cn.eainfo.navigation.model.EmergencyModel;
import cn.eainfo.navigation.model.NavigationModel;
import cn.eainfo.navigation.po.Emergency;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.service.EmergencyService;
import cn.eainfo.navigation.service.NavigationService;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysUserService;

/**
 * <p>文件名称: EmergencyAction.java </p>
 * <p>文件描述: &lt;描述&gt; </p>
 * <p>版权所有:  Copyright (c) qixin Coperation</p>
 * <p>公    司: 南京企信科技有限公司</p>
 * <p>内容摘要: 无</p>
 * <p>其他说明: 无</p>
 * <p>创建日期：2014-5-6</p>
 * <p>完成日期：2014-5-6</p>
 * <p>修改记录1: // 修改历史记录，包括修改日期、修改者及修改内容</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：Administrator
 *    修改内容：
 * </pre>
 * <p>修改记录2：…</p>
 * @version 1.0
 * @author huxiangyu
 */
public class EmergencyAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private EmergencyService emergencyService;
	// 用户管理接口
	private SysUserService sysUserService;
	private SysAreaService sysAreaService;
	private String cityChName;
	private long id;
	private String areaNo;
	private String type;
	private String emergenceyRedirect;
	
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
	
	private String areaString;
	private String areaStrings;
	private String jsonString;
	private List areaList;
	
	
	
	public String ajaxDelete() throws Exception {
		logger.log(Level.INFO,"ajaxDelete()导航信息删除开始");
		emergencyService.deleteEmergence(id);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("success");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		logger.log(Level.INFO,"ajaxDelete()导航信息删除结束");
		return "jsonString";
	}
	/** 
     * <设置返回的地区字符串>
     * @param SysAreaModel
     * @see [类、类#方法、类#成员]
     */
    public void setPageAreaString(SysAreaModel SysAreaModel){
    	areaList = sysAreaService.getSysAreaList(SysAreaModel);
    	areaNo = ((SysArea) areaList.get(0)).getAreaNo();
		String menu = "<select id='areaNo' name='areaNo' class='easyui-combobox' editable='false'>";
    	//String menu = "<select id='areaNo' name='areaNo'>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaString = areaString + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		
		areaString = menu + areaString + "</select>";
		String menus = "<select id='areaNos' name='areaNos' class='easyui-combobox' editable='false'>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaStrings = areaStrings + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		areaStrings = menus + areaStrings + "</select>";
    }
	/** 
	 * <查询页面list>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String forwordList() throws Exception{
		logger.log(Level.INFO,"forwordList()初始化导航页面查询开始");
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		//当用户过期 则不予处理
		if(sysUser!=null){
			sm.setAreaNo(sysUser.getAreaNo());
			setPageAreaString(sm);
		}
		//入过登陆超时则直接跳转
		else{
			return "timeout";
		}
		logger.log(Level.INFO,"forwordList()初始化导航页面查询结束");
		return "forwordList";
	}
	
	/** 
	 * <导航管理的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList() throws Exception {
		logger.log(Level.INFO,"ajaxList()导航信息查询开始");
		EmergencyModel emergencyModel = new EmergencyModel();
		emergencyModel.setAreaNo(areaNo);
		emergencyModel.setType(type);
		emergencyModel.setPage(page);
		emergencyModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData = emergencyService.getEmergencyPage(emergencyModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		logger.log(Level.INFO,"ajaxList()导航信息查询结束");
		return "jsonString";
	}
	/** 
	 * <新曾或修改>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxSaveOrUpdate(){
		logger.log(Level.INFO,"ajaxSaveOrUpdate()导航应急更新开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","导航应急更新失败","emergency");
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			Emergency emergency = new Emergency();
			emergency.setAreaNo(areaNo);
			emergency.setType(type);
			emergency.setEmergenceyRedirect(emergenceyRedirect);
			//若是修改且状态不为保存
			if(id!=0){
				emergency.setId(id);
				emergencyService.edit(emergency);
			}
			//若是新增
			else{
				emergencyService.insert(emergency);
			}
			ajaxBeanData = new AjaxBeanData("success","导航应急信息更新成功","navigation");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			logger.log(Level.INFO,"ajaxSaveOrUpdate()导航应急信息更新结束");
		}catch(Exception e){
			logger.log(Level.INFO,"ajaxSaveOrUpdate()导航应急信息更新异常"+e);
		}
		return "jsonString";
	}
	
	/**
	 * 获取导航应急信息
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		Emergency emergency = new Emergency();
		emergency = emergencyService.getBeanById(id);
		jsonString = JSONObjectTool.getObjectJson(emergency);
		return "jsonString";
	}
	
	/** 
	 * <获取导航应急信息>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxGetEmergencyRedirect(){
		logger.log(Level.INFO,"ajaxGetEmergencyRedirect()导航应急信息获取开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","导航应急获取失败","emergency");
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			EmergencyModel emergencyModel = new EmergencyModel();
			emergencyModel.setAreaNo(areaNo);
			emergencyModel.setType(type);
			Emergency emergency = emergencyService.getBeanByTypeAndAreaNo(emergencyModel);
			if(emergency==null){
				ajaxBeanData = new AjaxBeanData("success","","emergency");
			}else{
				ajaxBeanData = new AjaxBeanData("success",emergency.getEmergenceyRedirect(),"emergency");
			}
			logger.log(Level.INFO,"ajaxGetEmergencyRedirect()导航应急信息获取结束");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		}catch(Exception e){
			
		}
		return "jsonString";
	}
	/**
	 * @return 返回 emergencyService
	 */
	public EmergencyService getEmergencyService() {
		return emergencyService;
	}
	/**
	 * @param 对emergencyService进行赋值
	 */
	public void setEmergencyService(EmergencyService emergencyService) {
		this.emergencyService = emergencyService;
	}
	/**
	 * @return 返回 sysUserService
	 */
	public SysUserService getSysUserService() {
		return sysUserService;
	}
	/**
	 * @param 对sysUserService进行赋值
	 */
	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	/**
	 * @return 返回 cityChName
	 */
	public String getCityChName() {
		return cityChName;
	}
	/**
	 * @param 对cityChName进行赋值
	 */
	public void setCityChName(String cityChName) {
		this.cityChName = cityChName;
	}
	/**
	 * @return 返回 id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param 对id进行赋值
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return 返回 emergenceyRedirect
	 */
	public String getEmergenceyRedirect() {
		return emergenceyRedirect;
	}
	/**
	 * @param 对emergenceyRedirect进行赋值
	 */
	public void setEmergenceyRedirect(String emergenceyRedirect) {
		this.emergenceyRedirect = emergenceyRedirect;
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
	 * @return 返回 sysAreaService
	 */
	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}
	/**
	 * @param 对sysAreaService进行赋值
	 */
	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}
	/**
	 * @return 返回 areaString
	 */
	public String getAreaString() {
		return areaString;
	}
	/**
	 * @param 对areaString进行赋值
	 */
	public void setAreaString(String areaString) {
		this.areaString = areaString;
	}
	/**
	 * @return 返回 areaStrings
	 */
	public String getAreaStrings() {
		return areaStrings;
	}
	/**
	 * @param 对areaStrings进行赋值
	 */
	public void setAreaStrings(String areaStrings) {
		this.areaStrings = areaStrings;
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
	 * @return 返回 areaList
	 */
	public List getAreaList() {
		return areaList;
	}
	/**
	 * @param 对areaList进行赋值
	 */
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	
	
}
