/*
 * 文 件 名:  ShortcutsAction.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.common.file.CommonFileUtils;
import cn.eainfo.common.file.ImageFileUtils;
import cn.eainfo.navigation.model.NavigationModel;
import cn.eainfo.navigation.model.ShortcutsModel;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.ReleaseNavigationService;
import cn.eainfo.navigation.service.ShortcutsService;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;

import com.opensymphony.xwork2.ActionContext;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ShortcutsAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String areaNo;
	private String type;
	private String url;
	private String namena;
	
	private String icon;
	private File iconFile;
	private String iconFileFileName;
	private String iconFileContentType;
	private String checkState; 
	private Integer key;
	private long id;
	private ShortcutsService shortcutsService;
	private SysAreaService sysAreaService;
	private String areaString;
	private String areaStrings;
	private long page;
	private long rows;
	private String jsonString;
	private List areaList;
	private ReleaseNavigationService releaseNavigationService;
	
	
	/** 
	 * <判断某个快捷键是否已经存在>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String keyIsExist(){
		logger.log(Level.INFO,"keyIsExist()判断快捷位置是否已经被占用");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			Shortcuts shortcuts = new Shortcuts();
			shortcuts.setAreaNo(areaNo);
			shortcuts.setType(type);
			shortcuts.setKey(key);
			String  isExist = shortcutsService.keyIsExist(shortcuts);
			ajaxBeanData.setResult("success");
			ajaxBeanData.setResume(isExist);
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			logger.log(Level.INFO,"keyIsExist()判断快捷位置是否已经被占用结束");
		}catch(Exception e){
			logger.log(Level.ERROR,"keyIsExist()判断快捷位置是否已经被占用异常"+e);
			ajaxBeanData = new AjaxBeanData("faild","系统异常","short");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
	 	}finally{
			return "jsonString";
		}
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
	public String forwardList() throws Exception{
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		if(sysUser!=null&&sysUser.getAreaNo()!=null){
			sm.setAreaNo(sysUser.getAreaNo());
		}
		setPageAreaString(sm);
		return "forwordList";
	}
	/** 
	 * <快捷键的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList() throws Exception {
		//SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		ShortcutsModel shortcutsModel = new ShortcutsModel();
		shortcutsModel.setType(type);
		shortcutsModel.setAreaNo(areaNo);
		shortcutsModel.setCheckState(checkState);
		shortcutsModel.setPage(page);
		shortcutsModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData =  shortcutsService.getShortPage(shortcutsModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}
	/** 
	 * <快捷键的删除>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxDelete() throws Exception {
		shortcutsService.deleteShort(id);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}
	/** 
	 * <快捷键信息更新>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxSaveOrUpdate() throws Exception{
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		Shortcuts shortcuts = new Shortcuts();
		//前台控制好不允许选择江苏省来增加或修改
		List<String> cityEnNameList = releaseNavigationService.getCityName(areaNo);
		shortcuts.setKey(key);
		shortcuts.setNamena(namena);
		shortcuts.setAreaNo(areaNo);
		shortcuts.setType(type);
		shortcuts.setUrl(url);
		shortcuts.setCheckState("待审核");
		String saveImgMsg = "";
		//对图片名称进行
		String realFileName = "";
		if(iconFile!=null){
			realFileName = iconFileFileName.split("\\.")[0]+"_"+type+"."+iconFileFileName.split("\\.")[1];
		}
		if(id==0){
			saveImgMsg = dealShortImg();
			if(!saveImgMsg.equals("success")){
				ajaxBeanData = new AjaxBeanData("faild",saveImgMsg,"navigation");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return "jsonString";
			}
			//String a[] = iconFileFileName.split("\\.");
			String realPath = ServletActionContext.getServletContext().getRealPath("/Root")+"/"+cityEnNameList.get(0)+"/images/"+realFileName;
			File tempFile = new File(realPath);
			
			FileUtils.copyFile(iconFile, tempFile);
			shortcuts.setIcon("Root/"+cityEnNameList.get(0)+"/images/"+realFileName);
			//若没有主键则说明是新增
			shortcutsService.add(shortcuts);
		}else{
			shortcuts.setId(id);
			// 若是修改
			if(iconFile!=null){
				saveImgMsg = dealShortImg();
				if(!saveImgMsg.equals("success")){
					ajaxBeanData = new AjaxBeanData("faild",saveImgMsg,"navigation");
					jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
					return "jsonString";
				}
				
				String realPath = ServletActionContext.getServletContext().getRealPath("/Root")+"/"+cityEnNameList.get(0)+"/images/"+realFileName;
				File tempFile = new File(realPath);
				CommonFileUtils.createFile(tempFile, true);
				//保存快捷键图片
				FileUtils.copyFile(iconFile, tempFile);
				shortcuts.setIcon("Root/"+cityEnNameList.get(0)+"/images/"+realFileName);
			}
			else{
				shortcuts.setIcon(icon);
			}
			shortcutsService.edit(shortcuts);
		}
		
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}
	/** 
	 * <快捷键图片参数验证>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String dealShortImg(){
		logger.log(Level.ERROR,"dealShortImg()快捷键信息处理图片开始");
		String saveImgMsg = "";
		try{
			if(!iconFileContentType.equals("image/png")&&!iconFileContentType.equals("image/jpeg")&&!iconFileContentType.equals("image/gif")){
				saveImgMsg = "不支持的图片格式："+iconFileContentType;
				return saveImgMsg;
			}
			Map<String,Integer> map = ImageFileUtils.readImageFile(iconFile, iconFileContentType.split("/")[1]);
			
			//如果是快捷键保存图片,快捷键位置长宽大小未做限制
			if(key!=4){
				if(type.equals("hd")){
					if(map.get("width")!=186){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=106){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
				else{
					if(map.get("width")!=120){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=68){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
			}
			/*else{
				if(type.equals("hd")){
					if(map.get("width")!=186){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=106){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
				else{
					if(map.get("width")!=120){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=68){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
			}*/
			logger.log(Level.ERROR,"图片宽度："+map.get("width")+"图片高度为："+map.get("hidth"));
			saveImgMsg="success";
			logger.log(Level.ERROR,"dealShortImg()快捷键信息处理图片结束");
		}catch(Exception e){
			 logger.log(Level.ERROR,"dealShortImg()快捷键信息处理图片异常"+e);
	          e.printStackTrace();
	          saveImgMsg = "读取图片信息失败";
		}
		return saveImgMsg;
	}
	/**
	 * 获取快捷键
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		logger.log(Level.INFO, "ajaxGetBean()获取快捷键开始");
		Shortcuts shortcuts= shortcutsService.getShortBean(id);
		jsonString = JSONObjectTool.getObjectJson(shortcuts);
		logger.log(Level.INFO, "ajaxGetBean()获取快捷键结束");
		return "jsonString";
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
	 * @return 返回 size
	 */
	public long getRows() {
		return rows;
	}
	/**
	 * @param 对size进行赋值
	 */
	public void setRows(long rows) {
		this.rows = rows;
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
	 * @return 返回 url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param 对url进行赋值
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return 返回 namena
	 */
	public String getNamena() {
		return namena;
	}
	/**
	 * @param 对namena进行赋值
	 */
	public void setNamena(String namena) {
		this.namena = namena;
	}
	
	
	/**
	 * @return 返回 logger
	 */
	public Logger getLogger() {
		return logger;
	}
	/**
	 * @param 对logger进行赋值
	 */
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	/**
	 * @return 返回 icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param 对icon进行赋值
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return 返回 iconFile
	 */
	public File getIconFile() {
		return iconFile;
	}
	/**
	 * @param 对iconFile进行赋值
	 */
	public void setIconFile(File iconFile) {
		this.iconFile = iconFile;
	}
	/**
	 * @return 返回 key
	 */
	public Integer getKey() {
		return key;
	}
	/**
	 * @param 对key进行赋值
	 */
	public void setKey(Integer key) {
		this.key = key;
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
	 * @return 返回 iconFileFileName
	 */
	public String getIconFileFileName() {
		return iconFileFileName;
	}
	/**
	 * @param 对iconFileFileName进行赋值
	 */
	public void setIconFileFileName(String iconFileFileName) {
		this.iconFileFileName = iconFileFileName;
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
	 * @return 返回 iconFileContentType
	 */
	public String getIconFileContentType() {
		return iconFileContentType;
	}
	/**
	 * @param 对iconFileContentType进行赋值
	 */
	public void setIconFileContentType(String iconFileContentType) {
		this.iconFileContentType = iconFileContentType;
	}
	
}
