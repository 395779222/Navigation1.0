package cn.eainfo.navigation.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;





import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.common.SshUtil;
import cn.eainfo.common.file.CommonFileUtils;
import cn.eainfo.common.file.PropertiesFile;
import cn.eainfo.navigation.po.Release;
import cn.eainfo.navigation.service.ReleaseNavigationService;

/**
 * <功能详细描述>
 * @author huxiangyu
 * @version  [版本号, 2014-4-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ReleaseNavigationAction {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private ReleaseNavigationService releaseNavigationService;
	//若是传过来的是江苏省，则所有市都发布
	private String areaNo;
	//若传古来市空则俩种 （hd,sd）都发布
	private String type;
	private String jsonString;
	private String cardNo;
	/** 
	 * <生成html的数据的处理>
	 * @param cityEnName ：城市的拼音名称
	 * @param navigationType ： 导航的样式（sd,hd）
	 * @param releaseType:发布方式（预览，正式）getTest(预览测试)getNavigation()
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private boolean generateHtml(String cityEnName,String navigationType,String releaseType){
		logger.log(Level.INFO, " generateHtml()生成静态页面开始 BEGIN");
		boolean flag = false;
		String u="http://127.0.0.1:8080/Navigation1.0/navigation/getNavigation!"+releaseType+"?type="+navigationType+"&enname="+cityEnName;
		String path=PropertiesFile.getPropertiesValueByName("serviceRootAddress")+cityEnName;
		//String path="D:/Program/Tomcat 6.0/webapps/Navigation1.0/Root/"+cityEnName;
		//String path="/opt/onewave/ngdh/webapps/Navigation1.0/ROOT/"+cityEnName;
		 try{
			 if(releaseType.equals("getTest")){
				path = path+"/"+navigationType+"tv.jsp";
			 }
			 else{
				path = path +"/index_"+cityEnName+"_"+navigationType+".jsp";	
			 }
			 File file = new File(path);
			 CommonFileUtils.createFile(file, true);
		 }catch (Exception e) {
			logger.log(Level.ERROR, " generateHtml()生成静态页面出错");
			e.printStackTrace();
		}
		releaseNavigationService.JspToHtmlByURL(u,path);
		logger.log(Level.INFO, " 静态页面的地址为："+path);
		logger.log(Level.INFO, " generateHtml()生成静态页面结束");
		return flag;
	}
	/** 
	 * <预览发布>
	 * @return
	 * @throws Exception 
	 * @see [类、类#方法、类#成员]
	 */
	public String preRelease(){
		logger.log(Level.INFO, " preRealse()预览发布开始 BEGIN");
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		//String path="/opt/onewave/njgdsp/webapps/Navigation1.0/ROOT/";
		//boolean flag = false;
		try{
			releaseNavigationService.generateIndexJsp(areaNo);
			List<String>cityFileNameList = new ArrayList<String>();
			//不包含江苏省
			cityFileNameList = releaseNavigationService.getCityName(areaNo);
			if(cityFileNameList!=null&&cityFileNameList.size()>0){
				for(int i=0;i<cityFileNameList.size();i++){
					String cityName = cityFileNameList.get(i);
					List<Release> preReleaseList=releaseNavigationService.getServerList("0");
					//当发布制式不为空时
					if(type!=null&&!type.equals("")){
						//生成静态页面
						generateHtml(cityName,type,"getNavigation");
						//生成静态测试页面(hdtv.jsp sdtv.jsp)
						//generateHtml(cityName,type,"getTest");
						//发布信息
						release(preReleaseList,cityName);
					}
					else{
						//生成静态页面(高清和标清)
						generateHtml(cityName,"sd","getNavigation");
						generateHtml(cityName,"hd","getNavigation");
						//生成静态测试页面(高清和标清)
						//generateHtml(cityName,"sd","getTest");
						//generateHtml(cityName,"hd","getTest");
						//发布信息
						release(preReleaseList,cityName);
					}
				}
			}
	    ajaxBeanData = new AjaxBeanData("success","成功","navigation");
	    jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
	    logger.log(Level.INFO, " preRealse()预览发布结束");
		}catch(Exception e){
			logger.log(Level.ERROR, " preRealse()预览发布异常");
			e.printStackTrace();
		}finally{
			
		}
		return "jsonString";
	}
	/** 
	 * <发布信息处理>
	 * @param preReleaseList（发布服务器列表）
	 * @param cityEnName :城市名称（拼音）
	 * @param navigationType：sd,hd
	 * @param releaseType:发布方式：正式("formal")，预览测试("Tset")
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private boolean release(List<Release> preReleaseList,String cityEnName){
		logger.log(Level.INFO, " release()信息发布开始 BEGIN");
		boolean flag = false;
		//同步图片
		String address ="";
		String url="";
		try{
			if(preReleaseList!=null&&preReleaseList.size()>0){
				for(int i=0;i<preReleaseList.size();i++){
					//同步图片与文件(无乱测试还是同步都放在一个文件加下)因此发布只需要将城市里面的所有文件同步一下就好了
					address=preReleaseList.get(i).getAddress()+"/"+cityEnName;
					logger.log(Level.INFO, " release()图片和页面发布地址："+address);
					url =  preReleaseList.get(i).getUrl();
					synchrostep(preReleaseList.get(i),address,url);
					//同步index
					address = preReleaseList.get(i).getAddress()+"/index.jsp";
					logger.log(Level.INFO, " release()index.jsp发布地址："+address);
					synchrostep( preReleaseList.get(i), address,url);
				}
			}
			logger.log(Level.INFO, " release()信息发布结束");
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	/** 
	 * <正式发布>
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String officially() {
		AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		logger.log(Level.INFO, " officially()信息正式发布开始");
		//AjaxBeanData ajaxBeanData = new AjaxBeanData("faild","失败","navigation");
		List<Release> preReleaseList=releaseNavigationService.getServerList("1");
		List<String>cityFileNameList = new ArrayList<String>();
		//不包含江苏省
		cityFileNameList = releaseNavigationService.getCityName(areaNo);
		for(int i=0;i<cityFileNameList.size();i++){
			String cityName = cityFileNameList.get(i);
			release(preReleaseList,cityName);	
		}
		logger.log(Level.INFO, " officially()信息正式发布结束");
		ajaxBeanData = new AjaxBeanData("success","成功","navigation");
		try {
			jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		} catch (Exception e) {
			logger.log(Level.ERROR, " officially()发布异常");
			e.printStackTrace();
		}
		return "jsonString";
	}
	/** 
	 * <同步文件到服务器>
	 * @param release
	 * @param address
	 * @param fileurl
	 * @return
	 * @throws IOException 
	 * @see [类、类#方法、类#成员]
	 */
	private boolean synchrostep(Release release,String address,String fileurl){
		logger.log(Level.INFO, " synchrostep()信息同步开始");
		boolean flag = false;
		String cmd="rsync -vzrltopg " + address+" "+fileurl;
		logger.log(Level.INFO, " synchrostep()服务器IP地址为："+release.getIp());
		logger.log(Level.INFO, " synchrostep()信息同步源地址地址为："+address);
		logger.log(Level.INFO, " synchrostep()信息同步目的地址为："+fileurl);
		SshUtil sshUtil = new SshUtil(release.getIp(),Integer.parseInt(release.getPort()),release.getUserName(),release.getPass(),cmd);
		sshUtil.execCmd();
		return flag;
		
	}
	public String getUserCityName(){
		
		return "cardNo";
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
	 * @return 返回 realseService
	 */
	public ReleaseNavigationService getReleaseNavigationService() {
		return releaseNavigationService;
	}
	/**
	 * @param 对realseService进行赋值
	 */
	public void setReleaseNavigationService(ReleaseNavigationService releaseNavigationService) {
		this.releaseNavigationService = releaseNavigationService;
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
	 * @return 返回 cardNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param 对cardNo进行赋值
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
