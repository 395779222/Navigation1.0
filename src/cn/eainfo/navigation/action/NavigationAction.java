package cn.eainfo.navigation.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import cn.eainfo.common.excel.CommonDateUtils;
import cn.eainfo.common.excel.ExcelDeal;
import cn.eainfo.navigation.model.NavigationModel;
import cn.eainfo.navigation.po.Navigation;
import cn.eainfo.navigation.service.NavigationService;
import cn.eainfo.navigation.service.ReleaseNavigationService;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysRoleService;
import cn.eainfo.system.service.SysUserService;
import cn.eainfo.common.file.CommonFileUtils;
import cn.eainfo.common.file.ImageFileUtils;

import com.opensymphony.xwork2.ActionContext;
/**
 * <功能详细描述>
 * @author huxiangyu
 * @version  [版本号, 2014-4-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NavigationAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	//导航管理接口
	private NavigationService navigationService;
	// 用户管理接口
	private SysUserService sysUserService;
	// 角色管理接口
	
	private long id;
	// 新增/修改参数
	private Navigation navigation;
	private SysRoleService sysRoleService;
	private SysAreaService sysAreaService;
	private ReleaseNavigationService releaseNavigationService;

	private String url;
	private Integer location;
	private String namena;
	private String icon;
	private String icon2;
	private Integer num;
	private String type;
	private String domain;
	private String redirect;
	private String areaNo;
	private List areaList;
	private String state;
	private String checkText;
	private String areaString;
	private String areaStrings;
	private String jsonString;
	private String cityEnName;
	private String cityChName;
	//更新标志       ##保存##    ##提交审核##
    // 可以定义成数组类型，也可以定义成list  
    private List<File> uploadFiles; // 得到上传的文件,此属性对应于表单中文件字段的名称  
    
     // 下面的这两个属性的命名必须遵守上定的规则，即为"表单中文件字段的名称" + "相应的后缀"  
    //多个上传文件的类型集合  
    private List<String> uploadFilesContentType;  
  
    // 多个上传文件的文件名集合  
    private List<String> uploadFilesFileName; 
    //上传的excel文件
    private File excelFile;
    private String excelFileFileName;
    private long rows;//每页显示的记录数
	private long page;//当前第几页 
    
	/** 
	 * <获取所属地区指定类型的最大所在位置的值>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("finally")
	public String getMaxLoaction(){
		logger.log(Level.INFO,"getMaxLoaction()获取最大的导航位置开始");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			navigation = new Navigation();
			navigation.setAreaNo(areaNo);
			navigation.setType(type);
			navigation.setLocation(location);
			Integer locationMaxNum = navigationService.getMaxLoactionByAreaAndType(navigation);
			ajaxBeanData.setResult("success");
			ajaxBeanData.setResume(locationMaxNum.toString());
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			logger.log(Level.INFO,"getMaxLoaction()获取最大的导航位置结束");
		}catch(Exception e){
			logger.log(Level.ERROR,"getMaxLoaction()获取最大的导航位置系统异常"+e);
			ajaxBeanData = new AjaxBeanData("faild","系统异常","navigation");
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
			return "timeOut";
		}
		logger.log(Level.INFO,"forwordList()初始化导航页面查询结束");
		return "forwordList";
	}
	
	/** 
	 * <导航信息审核初始化页面>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String navigationMsgCheckList(){
		logger.log(Level.INFO,"navigationMsgCheckList()初始化导航审核开始");
		logger.log(Level.INFO,"navigationMsgCheckList()初始化导航审核结束");
		return "navigationMsgCheckList";
	}
	/** 
	 * <导航审核信息列表查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxCheckList()throws Exception{
		logger.log(Level.INFO,"ajaxCheckList()审核信息查询开始");
		NavigationModel navigationrModel = new NavigationModel();
		navigationrModel.setNamena(namena);
		navigationrModel.setAreaNo(areaNo);
		if(state==null||state.equals("")){
			navigationrModel.setState("审核");
		}
		navigationrModel.setType(type);
		navigationrModel.setPage(page);
		navigationrModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData =  navigationService.getNavigationPage(navigationrModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		logger.log(Level.INFO,"ajaxCheckList()审核信息查询结束");
		return "jsonString";
	}
	/** 
	 * <导航管理的查询>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxList() throws Exception {
		logger.log(Level.INFO,"ajaxList()导航信息查询开始");
		NavigationModel navigationrModel = new NavigationModel();
		navigationrModel.setNamena(namena);
		navigationrModel.setAreaNo(areaNo);
		navigationrModel.setNum(num);
		navigationrModel.setType(type);
		navigationrModel.setPage(page);
		navigationrModel.setPageSize(rows);
		AjaxJsonListData ajaxJsonListData =  navigationService.getNavigationPage(navigationrModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		logger.log(Level.INFO,"ajaxList()导航信息查询结束");
		return "jsonString";
	}
	/**
	 * 获取用户
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		navigation = navigationService.getNavigationBean(id);
		jsonString = JSONObjectTool.getObjectJson(navigation);
		return "jsonString";
	}
	/** 
	 * <导航管理的删除>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxDelete() throws Exception {
		logger.log(Level.INFO,"ajaxDelete()导航信息删除开始");
		navigation = navigationService.getNavigationBean(id);
		//设置操作时间
		String nowTime = CommonDateUtils.getNowDate("yyyy-MM-dd HH:mm:ss");
		navigation.setUpdateTime(nowTime);
		navigationService.deleteNavigation(id,navigation);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("success");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		logger.log(Level.INFO,"ajaxDelete()导航信息删除结束");
		return "jsonString";
	}
	
	/** 
	 * <新曾或修改>
	 * @return
	 * @throws Exception
	 * @see [类、类#方法、类#成员]
	 */
	public String ajaxSaveOrUpdate() throws Exception {
		logger.log(Level.INFO,"ajaxSaveOrUpdate()导航更新开始");
		navigation = new Navigation();
		//设置修改时间
		String nowTime = CommonDateUtils.getNowDate("yyyy-MM-dd HH:mm:ss");
		navigation.setUpdateTime(nowTime);
		navigation.setAreaNo(areaNo);
		navigation.setDomain(domain);
		navigation.setLocation(location);
		navigation.setNamena(namena);
		navigation.setNum(num);
		navigation.setRedirect(redirect);
		navigation.setType(type);
		navigation.setUrl(url);
		navigation.setState(state);
		if(checkText==null||checkText.equals("")){
			checkText="待审核";
		}
		//若是修改且状态不为保存
		if(id!=0){
			 ajaxEdit();
		}
		//若是新增
		else{
			ajaxAdd();
		}
		logger.log(Level.INFO,"ajaxSaveOrUpdate()导航更新结束");
		return "jsonString";
	}
	/** 
	 * <新增导航信息>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	private void ajaxAdd() throws Exception {
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		//保存图片
		String msg = saveImg();
		if(!msg.equals("success")){
			ajaxBeanData = new AjaxBeanData("faild",msg,"navigation");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			return;
		}else{
			//设置未选中图标保存在服务器的地址
			navigation.setIcon("Root/"+cityEnName+"/images"+"/"+uploadFilesFileName.get(0).split("\\.")[0]+"_"+type+"."+uploadFilesFileName.get(0).split("\\.")[1]);
			//设置选中图标保存在服务器的地址
			navigation.setIcon2("Root/"+cityEnName+"/images"+"/"+uploadFilesFileName.get(1).split("\\.")[0]+"_"+type+"."+uploadFilesFileName.get(1).split("\\.")[1]);
			//设置导航的审核状态
			
			try {
				navigationService.addNavigation(navigation);
				ajaxBeanData.setResult("success");
				ajaxBeanData.setResume("成功");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			} catch (Exception e) {
				e.printStackTrace();
				logger.log(Level.ERROR,"ajaxAdd()导航信息新增异常");
			}
			
		}
		
	}
	
	/** 
	 * <保存图片>
	 * @throws IOException
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("finally")
	private String  saveImg() throws IOException{
		String saveImgMsg = "保存图片失败";
		logger.log(Level.ERROR,"saveImg()导航信息保存图片开始");
		FileOutputStream fos = null;
        FileInputStream fis = null;
      //对图片名称进行
        String realFileName = "";
		try {
			//前台控制好不允许选择江苏省来增加或修改
			List<String> cityEnNameList = releaseNavigationService.getCityName(areaNo);
			String realPath = ServletActionContext.getServletContext().getRealPath("/Root")+"/"+cityEnNameList.get(0)+"/images";
			cityEnName = cityEnNameList.get(0);
			File pathFile = new File(realPath);
			if(!pathFile.exists()&&!pathFile.isDirectory()){
				pathFile.mkdirs();
			}
			/*
			 * 获取图片的文件类型，若不满足条件则则直接返回
			 * */
			for(int j=0;j<uploadFilesContentType.size();j++){
				//这里获取的格式是image/jpg此类的格式因此在进行图片解析的时候要处理一下
				if(!uploadFilesContentType.get(j).equals("image/png")&&!uploadFilesContentType.get(j).equals("image/jpeg")&&!uploadFilesContentType.get(j).equals("image/gif")){
					saveImgMsg = "不支持的图片格式："+uploadFilesContentType.get(j);
					return saveImgMsg;
				}
				
			}
			for(int i=0;i<uploadFiles.size();i++){
				//定义二次文件名称+高标清后缀
				realFileName = uploadFilesFileName.get(i).split("\\.")[0]+"_"+type+"."+uploadFilesFileName.get(i).split("\\.")[1];
				Map<String,Integer> map = ImageFileUtils.readImageFile(uploadFiles.get(i), uploadFilesContentType.get(i).split("/")[1]);
				/*图片的长宽验证*/
				if(type.equals("hd")){
					if(map.get("width")!=161){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=116){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
				else{
					if(map.get("width")!=105){
						saveImgMsg = "宽度："+map.get("width")+" 的图片不符合要求";
						return saveImgMsg;
					}
					if(map.get("hidth")!=75){
						saveImgMsg = "高度："+map.get("hidth")+"的图片不符合要求";
						return saveImgMsg;
					}
				}
				File tempFile = new File(realPath+"/"+realFileName);
				//获取输出流
				fos = new FileOutputStream(tempFile);
				//获取图片输入流
				fis = new FileInputStream(uploadFiles.get(i));
				byte[] buffer = new byte[1024];
		        int len = 0;
		        while ((len = fis.read(buffer)) > 0) {
		        	//写入文件
		            fos.write(buffer, 0, len);
		        }
			}
			logger.log(Level.ERROR,"saveImg()导航信息保存图片结束");
			saveImgMsg = "success";
        } catch (Exception e) {
            System.out.println("文件上传失败");
            logger.log(Level.ERROR,"saveImg()导航信息保存图片异常");
            e.printStackTrace();
            saveImgMsg = "读取图片信息失败";
        } 
		if(fos!=null){
    		fos.close();
    	}
    	if(fis!=null){
    		fis.close();
    	}
    	return saveImgMsg;
	}
	
	/** 
	 * <功能详细描述>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	private void ajaxEdit() throws Exception {

		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		//修改要对ID进行赋值
		navigation.setId(id);
		//若是图片修改了
		if(null!=uploadFiles){
			String msg = saveImg();
			if(!msg.equals("success")){
				ajaxBeanData = new AjaxBeanData("faild",msg,"navigation");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return;
			}
			//设置未选中图标保存在服务器的地址（图片文件名称+高标清后缀）
			navigation.setIcon("Root/"+cityEnName+"/images/"+uploadFilesFileName.get(0).split("\\.")[0]+"_"+type+"."+uploadFilesFileName.get(0).split("\\.")[1]);
			//设置选中图标保存在服务器的地址（图片文件名称+高标清后缀）
			navigation.setIcon2("Root/"+cityEnName+"/images/"+uploadFilesFileName.get(1).split("\\.")[0]+"_"+type+"."+uploadFilesFileName.get(1).split("\\.")[1]);
		}
		else{
			navigation.setIcon(icon);
			navigation.setIcon2(icon2);
		}
		try {
			navigationService.updateNavigation(navigation);
			ajaxBeanData.setResult("success");
			ajaxBeanData.setResume("成功");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		} catch (Exception e) {
			e.printStackTrace();
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
		//设置保存的临时路径
		String path=ServletActionContext.getServletContext().getRealPath("/excel")+"/"+excelFileFileName;
		CommonFileUtils.createFile(path, true);
		//取得将要临时存储的文件
		File file = new File(path);
		byte[] bytes = null;
		try{
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
			//创建excel文件
			 //file.createNewFile();
			// 写入临时文件中
			 bytes = CommonFileUtils.toByteArray(excelFile);
			if(!CommonFileUtils.writeByteArrToFile(bytes, file)){
				ajaxBeanData  = new AjaxBeanData("faild","excel写入临时文件失败","navigation");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return jsonString;
			}
			//构造要读取到map中的字段名称
			ExcelDeal excelDeal = new ExcelDeal();
			//excel表的字段位置和字段名称
			excelDeal.setIndexName(new String[]{"namena","url","domain","redirect","num","location","icon","icon2","type","state","areaNo","updateTime","checkText"});
			// 设置开始行数
			excelDeal.setStartIndex(1);
			List<Map<String, Object>> list = null;
			//读取excel ->list
			list = excelDeal.parseExcelToList(file);
			// 删除excel文件
			if(file.exists()){
				try {
					file.delete();
				} catch (Exception e2) {
					file = null;
				}
			}
			List<Navigation>navList = new ArrayList<Navigation>();
			String msg = mapListToNavList(list,navList);
			if(msg==null){
				ajaxBeanData = new AjaxBeanData("faild","解析异常","navigation");
				jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
				return "jsonString";
			}
			logger.log(Level.INFO,"删除地区导航信息开始");
			navigationService.deleteNavigationListByAreaNo(navList.get(0));
			logger.log(Level.INFO,"删除地区导航信息成功");
			logger.log(Level.INFO,"保存导入信息开始");
			for(int i = 0;i<navList.size();i++){
				navigationService.addNavigation(navList.get(i));
			}
			logger.log(Level.INFO,"保存导入信息成功");
			ajaxBeanData = new AjaxBeanData("success","成功","navigation");
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		}catch(Exception e){
			e.printStackTrace();
			logger.log(Level.ERROR,"upLoadExcel()导入excel出错了！！！！！！！！！！！！");
		}
		return "jsonString";
	}
    /** 
     * <把解析出来的list转化为navList>
     * @param excleObjs
     * @param navList
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String mapListToNavList(List<Map<String, Object>> excleObjs, List<Navigation> navList){
    	String msg = null;
    	if(excleObjs.isEmpty()){
			// excel为空
			return "请填写要导入的导航信息！";
		}
    	//定义临时的导航对象
    	for(Map<String, Object> map : excleObjs){
    		//得在这边声明
    		Navigation nav = new Navigation();
    		nav.setNamena((String)map.get("namena"));
    		if((String)map.get("domain")==null||((String)map.get("domain")).equals("null")){
    			nav.setDomain((String)map.get(""));
    		}
    		else{
    			nav.setDomain((String)map.get("domain"));
    		}
    		nav.setUrl((String)map.get("url"));
    		nav.setIcon((String)map.get("icon"));
    		nav.setIcon2((String)map.get("icon2"));
    		
    		if((String)map.get("redirect")==null||((String)map.get("redirect")).equals("null")){
    			nav.setRedirect((String)map.get(""));
    		}
    		else{
    			nav.setRedirect((String)map.get("redirect"));
    		}
    		
    		nav.setNum(Integer.parseInt((String) map.get("num")));
    		nav.setLocation(Integer.parseInt((String) map.get("location")));
    		nav.setState((String)map.get("state"));
    		nav.setType((String)map.get("type"));
    		nav.setAreaNo((String)map.get("areaNo"));
    		nav.setCheckText((String)map.get("checkText"));
    		nav.setUpdateTime((String)map.get("updateTime"));
    		navList.add(nav);
    	}
    	msg = "success";
    	return msg;
    }
	/*导航信息导出
	 * */
	public void navigationExport(){
		try{
			logger.log(Level.INFO,"navigationExport()导出excel开始");
			NavigationModel navigationrModel = new NavigationModel();
			navigationrModel.setAreaNo(areaNo);
			navigationrModel.setType(type);
			//获取导航列表的信息
			List navList = navigationService.getExportList(navigationrModel);
			logger.log(Level.INFO,"navigationExport()获取导航列表成功##############");
			String strCHArray[] = {"业务名称","跳转地址","域名","实际地址","所在页面" ,"所在位置","未选中图标","选中图标","类型","状态","地区编号","更新时间","审核语"};
			String strENArray[]={"NAMENA","URL","DOMAIN","REDIRECT","NUM","LOCATION","ICON","ICON2","TYPE","STATE","AREANO","UPDATETIME","CHECKTEXT"};
			ExcelDeal excelDeal = new ExcelDeal();
			//获取生成的excel对象
			HSSFWorkbook wb = excelDeal.generateExcel(strCHArray,navList,strENArray);
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format =new SimpleDateFormat("yyyyMMddHHmmss");
			String time=format.format(cal.getTime());
			String path=ServletActionContext.getServletContext().getRealPath("/excel")+"/"+time+".xls";
			File file= new File(path);
			//创建文件夹如果不存在
			CommonFileUtils.createFile(file, true);
			FileOutputStream fout = new FileOutputStream(file);
			wb.write(fout);
			fout.close();
			//获取response对象
			HttpServletResponse response = ServletActionContext.getResponse();
			BufferedInputStream fis=null;
			BufferedOutputStream toClient=null;
			//清空response
			response.reset();
		    //设置response头
		    response.setContentType("application/excel");
		    response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
		    //以流的形式下载文件
		    fis = new BufferedInputStream(new FileInputStream(path));
		    //读取字节数
		    byte[] buffer = new byte[fis.available()]; 
		    fis.read(buffer); 
		    fis.close(); 
		    toClient = new BufferedOutputStream(response.getOutputStream()); 
		    toClient.write(buffer);
		    toClient.flush();
		    toClient.close();
		    // 将文件删除掉
			if(null != file && file.exists()){
				file.delete();
			}
		}catch(Exception e){
			logger.log(Level.ERROR,"navigationExport()导出 excel异常##############");
			e.printStackTrace();
		}
	}
	/**
	 * @param 对realseService进行赋值
	 */
	public void setReleaseNavigationService(ReleaseNavigationService releaseNavigationService) {
		this.releaseNavigationService = releaseNavigationService;
	}
	/**
	 * @return 返回 releaseNavigationService
	 */
	public ReleaseNavigationService getReleaseNavigationService() {
		return releaseNavigationService;
	}
	/**
	 * @return 返回 uploadFiles
	 */
	public List<File> getUploadFiles() {
		return uploadFiles;
	}

	/**
	 * @param 对uploadFiles进行赋值
	 */
	public void setUploadFiles(List<File> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	/**
	 * @return 返回 uploadFilesContentType
	 */
	public List<String> getUploadFilesContentType() {
		return uploadFilesContentType;
	}

	/**
	 * @param 对uploadFilesContentType进行赋值
	 */
	public void setUploadFilesContentType(List<String> uploadFilesContentType) {
		this.uploadFilesContentType = uploadFilesContentType;
	}

	/**
	 * @return 返回 uploadFilesFileName
	 */
	public List<String> getUploadFilesFileName() {
		return uploadFilesFileName;
	}

	/**
	 * @param 对uploadFilesFileName进行赋值
	 */
	public void setUploadFilesFileName(List<String> uploadFilesFileName) {
		this.uploadFilesFileName = uploadFilesFileName;
	}

	/**
	 * @return 返回 sysRoleService
	 */
	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}
	/**
	 * @param 对sysRoleService进行赋值
	 */
	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
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
	 * @return 返回 location
	 */
	public Integer getLocation() {
		return location;
	}
	/**
	 * @param 对location进行赋值
	 */
	public void setLocation(Integer location) {
		this.location = location;
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
	 * @return 返回 icon2
	 */
	public String getIcon2() {
		return icon2;
	}
	/**
	 * @param 对icon2进行赋值
	 */
	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}
	/**
	 * @return 返回 num
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * @param 对num进行赋值
	 */
	public void setNum(Integer num) {
		this.num = num;
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
	 * @return 返回 domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param 对domain进行赋值
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @return 返回 redirect
	 */
	public String getRedirect() {
		return redirect;
	}
	/**
	 * @param 对redirect进行赋值
	 */
	public void setRedirect(String redirect) {
		this.redirect = redirect;
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
	 * @return 返回 navigation
	 */
	public Navigation getNavigation() {
		return navigation;
	}

	/**
	 * @param 对navigation进行赋值
	 */
	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
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
	
}
