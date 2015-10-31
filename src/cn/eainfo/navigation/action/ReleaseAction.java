/*
 * 文 件 名:  ReleaseAction.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.common.excel.ExcelDeal;
import cn.eainfo.common.file.CommonFileUtils;
import cn.eainfo.navigation.model.ReleaseModel;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.po.Release;
import cn.eainfo.navigation.po.Shortcuts;
import cn.eainfo.navigation.service.ReleaseService;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;


import com.opensymphony.xwork2.ActionContext;

/**
 * <服务信息管理>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ReleaseAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private String areaString;
	private String areaStrings;
	private ReleaseService releaseService;
	private SysAreaService sysAreaService;

	private long id;
	private String name;
	private String ip;
	private String port;
	private String userName;
	private String pass;
	private String address;
	private String type;
	private String url;
	private String areaNo;
	private String deleteFlag;
    private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private List areaList;
	private String jsonString;
	
    private File excelFile;
    private String excelFileFileName;

	/** 
	 * <服务器信息导出>
	 * @see [类、类#方法、类#成员]
	 */
	public void releaseExport(){
		Release release = new Release();
		logger.log(Level.INFO,"releaseExport()导出服务器信息excel开始");
		try{
			List releaseList = releaseService.getAllRelease(release);
			String strCHArray[] = {"服务器名称","主机地址","端口","登录名","密码" ,"原路径","正式库路径","所属地区","类型","状态"};
			String strENArray[]={"NAME","IP","PORT","USERNAME","PASS","ADDRESS","URL","AREANO","TYPE","DELETEFLAG"};
			ExcelDeal excelDeal = new ExcelDeal();
			
			HSSFWorkbook wb = excelDeal.generateExcel(strCHArray,releaseList,strENArray);
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format =new SimpleDateFormat("yyyyMMddHHmmss");
			String time=format.format(cal.getTime());
			String path=ServletActionContext.getServletContext().getRealPath("/excel")+"/"+time+".xls";
			File file= new File(path);
			
			CommonFileUtils.createFile(file, true);
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.close();
			HttpServletResponse response = ServletActionContext.getResponse();
			BufferedInputStream fis=null;
			BufferedOutputStream toClient=null;
			response.reset();
		    response.setContentType("application/excel");
		    response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
		    fis = new BufferedInputStream(new FileInputStream(path));
		    byte[] buffer = new byte[fis.available()]; 
		    fis.read(buffer); 
		    fis.close(); 
		    toClient = new BufferedOutputStream(response.getOutputStream()); 
		    toClient.write(buffer);
		    toClient.flush();
		    toClient.close();
		   
			if(null != file && file.exists()){
				file.delete();
			}
		}catch(Exception e){
			logger.log(Level.ERROR,"releaseExport()导出 excel异常##############"+e);
		}
		
	   
	}
	/** 
	 * <导航信息excel导入>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String upLoadExcel(){
		logger.log(Level.INFO,"upLoadExcel()导入excel开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		String path=ServletActionContext.getServletContext().getRealPath("/excel")+"/"+excelFileFileName;
		CommonFileUtils.createFile(path, true);
		//取得将要临时存储的文件
		File file = new File(path);
		byte[] bytes = null;
		try{
			// 写入临时文件中
			bytes = CommonFileUtils.toByteArray(excelFile);
			if(!CommonFileUtils.writeByteArrToFile(bytes, file)){
				ajaxBeanData  = new AjaxBeanData("faild","excel写入临时文件失败","navigation");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return jsonString;
			}
			ExcelDeal excelDeal = new ExcelDeal();
			excelDeal.setIndexName(new String[] {"name","ip","port","userName","pass","address","url","areaNo","type","deleteFlag"});
			excelDeal.setStartIndex(1);
			List<Map<String, Object>> list = null;
			list = excelDeal.parseExcelToList(file);
			if(file.exists()){
				try {
					file.delete();
				} catch (Exception e2) {
					file = null;
				}
			}
			List<Release>releaseList = new ArrayList<Release>();
			String msg = mapListToReleaseList(list,releaseList);
			if(msg==null){
				ajaxBeanData = new AjaxBeanData("faild","解析异常","release");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return "jsonString";
			}
			logger.log(Level.INFO,"删除服务器信息开始");
			releaseService.deleteAllRelease();
			logger.log(Level.INFO,"删除服务器信息成功");
			logger.log(Level.INFO,"保存导入的服务器信息开始");
			for(int i = 0;i<releaseList.size();i++){
				releaseService.add(releaseList.get(i));
			}
			logger.log(Level.INFO,"保存导入的服务器信息信息成功");
			ajaxBeanData = new AjaxBeanData("success","成功","release");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		}catch(Exception e){
			
		}
		return "jsonString";
	}
	/** 
	 * <将解析出来的服务器列表转化为list>
	 * @param list
	 * @param releaseList
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String mapListToReleaseList(List<Map<String, Object>> excleObjs,List<Release> releaseList) {
		String msg = null;
    	if(excleObjs.isEmpty()){
			// excel为空
			return "请填写要导入的导航信息！";
		}
    	
    	for(Map<String, Object> map : excleObjs){
    		//得在这边声明
    		//定义临时的服务器对象
    		Release release = new Release();
    		release.setAddress((String)map.get("address"));
    		release.setAreaNo((String)map.get("areaNo"));
    		release.setDeleteFlag((String)map.get("deleteFlag"));
    		release.setIp((String)map.get("ip"));
    		release.setName((String)map.get("name"));
    		release.setPass((String)map.get("pass"));
    		release.setPort((String)map.get("port"));
    		release.setType((String)map.get("type"));
    		release.setUrl((String)map.get("url"));
    		release.setUserName((String)map.get("userName"));
    		releaseList.add(release);
    	}
    	msg = "success";
    	return msg;
	}
	/** 
     * <设置返回的地区字符串>
     * @param SysAreaModel
     * @see [类、类#方法、类#成员]
     */
    public void setPageAreaString(SysAreaModel SysAreaModel){
    	areaList = sysAreaService.getSysAreaList(SysAreaModel);
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
	public String forwordList() throws Exception{
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		//session会失效
		if(sysUser!=null){
			sm.setAreaNo(sysUser.getAreaNo());
		}
		else{
			return "timeout";
		}
		setPageAreaString(sm);
		return "forwordList";
	}
	/** 
	 * <服务器的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList()  {
		logger.log(Level.INFO,"ajaxList()查询服务器信息开始");
		try{
			//SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
			ReleaseModel releaseModel = new ReleaseModel();
			releaseModel.setName(name);
			releaseModel.setDeleteFlag(deleteFlag);
			releaseModel.setAreaNo(areaNo);
			releaseModel.setType(type);
			releaseModel.setPage(page);
			releaseModel.setPageSize(rows);
			AjaxJsonListData ajaxJsonListData =  releaseService.getReleasePage(releaseModel);
			jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
			logger.log(Level.INFO,"ajaxList()查询服务器信息结束");
		}catch(Exception e){
			logger.log(Level.ERROR,"ajaxList()查询服务器信息异常");
		}
		
		
		return "jsonString";
	}
	/** 
	 * <服务器的信息删除>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxDelete() throws Exception {
		releaseService.deleteRelease(id);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}
	/** 
	 * <服务器的信息新增>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxSaveOrUpdate() throws Exception{
		Release release = new Release();
		release.setAddress(address);
		release.setAreaNo(areaNo);
		release.setIp(ip);
		release.setPass(pass);
		release.setPort(port);
		release.setName(name);
		release.setUserName(userName);
		release.setType(type);
		release.setUrl(url);
		release.setId(id);
		release.setDeleteFlag(deleteFlag);
		if(id==0){
			releaseService.add(release);
		}
		else{
			releaseService.etid(release);
		}
		
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}
	/**
	 * 获取服务器
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		logger.log(Level.INFO, "ajaxGetBean()获取快捷键开始");
		Release release= releaseService.getShortBean(id);
		jsonString = JSONObjectTool.getObjectJson(release);
		logger.log(Level.INFO, "ajaxGetBean()获取快捷键结束");
		return "jsonString";
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
	 * @return 返回 releaseService
	 */
	public ReleaseService getReleaseService() {
		return releaseService;
	}
	/**
	 * @param 对releaseService进行赋值
	 */
	public void setReleaseService(ReleaseService releaseService) {
		this.releaseService = releaseService;
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
	 * @return 返回 name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 对name进行赋值
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回 ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param 对ip进行赋值
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return 返回 port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param 对port进行赋值
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return 返回 username
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param 对username进行赋值
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return 返回 pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param 对pass进行赋值
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return 返回 address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param 对address进行赋值
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return 返回 deleteFlag
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param 对deleteFlag进行赋值
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @return 返回 excelFile
	 */
	public File getExcelFile() {
		return excelFile;
	}
	/**
	 * @param 对excelFile进行赋值
	 */
	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}
	/**
	 * @return 返回 excelFileFileName
	 */
	public String getExcelFileFileName() {
		return excelFileFileName;
	}
	/**
	 * @param 对excelFileFileName进行赋值
	 */
	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}
	
}
