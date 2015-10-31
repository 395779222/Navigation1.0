 
package cn.eainfo.common;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.eainfo.common.file.PropertiesFile;
import cn.eainfo.navigation.webService.CoshipService4Business;
import cn.eainfo.navigation.webService.QueryInfo;
import cn.eainfo.navigation.webService.ResultInfo;

/**
 * <webservice获取机顶盒用户的信息>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CustomerInfoWebService {
	private Logger logger = Logger.getLogger("CustomerInfoWebService.class");
	public  ResultInfo getUserInfo(QueryInfo queryInfo){
		URL wsdlURL=null;
		ResultInfo resultInfo = new ResultInfo();
		try {
			long startTime=System.currentTimeMillis();   //获取开始时间
			logger.log(Level.INFO, " getUserInfo()接口查询用户信息 开始 BEGIN");
			//String a = PropertiesFile.getPropertiesValueByName("serviceIndexAddress");
			wsdlURL = new URL(PropertiesFile.getPropertiesValueByName("webServiceAddress"));
			//wsdlURL = new URL("http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl");
			//wsdlURL = new URL("http://172.31.177.244:18080/aaa/Business/CoshipService?wsdl");
			logger.log(Level.INFO, "定义接口服务器地址");
			QName serviceQName = new QName("http://service.business.coship.com/", "Service4business");
			logger.log(Level.INFO, "定义接口端口地址");
			QName portQName = new QName("http://service.business.coship.com/", "CoshipService4BusinessPort");
			logger.log(Level.INFO, "接口对象定义创建");
			Service service = Service.create(wsdlURL, serviceQName); 
			CoshipService4Business port = (CoshipService4Business) service.getPort(portQName, CoshipService4Business.class);
			logger.log(Level.INFO, "设置查询类型为1");
			queryInfo.setQueryType("1");
			logger.log(Level.INFO, " port.queryUserInfo:掉接口查询开始");
			resultInfo = port.queryUserInfo(queryInfo);
			logger.log(Level.INFO, " port.queryUserInfo:掉接口查询结束");
			if(resultInfo!=null&&resultInfo.getUserInfo()!=null){
				logger.log(Level.INFO, " 接口查询的卡号所属分公司编码为："+resultInfo.getUserInfo().getDistrictCode());
			}
			long endTime=System.currentTimeMillis(); //获取结束时间
			System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
			logger.log(Level.INFO, " port.queryUserInfo:结束");
			logger.log(Level.INFO, " port.queryUserInfo:结束");
		} catch (MalformedURLException e) {
			logger.log(Level.ERROR, " getUserInfo()接口查询用户信息 异常");
			e.printStackTrace();
		}
		return resultInfo;
	}
	public static void main(String args[]) throws DocumentException{
		/*QueryInfo queryInfo = new QueryInfo();
		queryInfo.setSmartCardId("825010288699921");
		ResultInfo resultInfo = getUserInfo(queryInfo);
		System.out.println(resultInfo.getUserInfo().getDistrictCode());*/
		String aaa ="<?xml version='1.0' encoding='GB2312'?><result status='0' message='同步成功' updatecount='1'></result>";
    	Document document = DocumentHelper.parseText(aaa);
		Element rootElement = document.getRootElement();
		String status = rootElement.attributeValue("status");
		String message = rootElement.attributeValue("message");
		if(status!=null&&!"".equals(status)&&"0".equals(status)){	
			 System.out.println("result===============>   "+status);   
		}
	}
	
}
