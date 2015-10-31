/*
 * 文 件 名:  Test.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-10
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.action;


import cn.eainfo.navigation.webService.CoshipService4Business;
import cn.eainfo.navigation.webService.QueryInfo;
import cn.eainfo.navigation.webService.ResultInfo;

import java.net.MalformedURLException;
import java.net.URL;  

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Test {
	public static void main(String args[]){
		try {
			URL wsdlURL = new URL("http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl");
			QName serviceQName = new QName("http://service.business.coship.com/", "Service4business");
			QName portQName = new QName("http://service.business.coship.com/", "CoshipService4BusinessPort");
			Service service = Service.create(wsdlURL, serviceQName); 
			CoshipService4Business port = (CoshipService4Business) service.getPort(portQName, CoshipService4Business.class);
			QueryInfo queryInfo = new QueryInfo();
			queryInfo.setQueryType("1");
			queryInfo.setSmartCardId("www");
			ResultInfo resultInfo = port.queryUserInfo(queryInfo);
			System.out.print(resultInfo);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}  
		/*try {
			String obj[] = {"11","","","1"};
			Client client = new Client(new URL("http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl"));
			Object[] results = client.invoke("queryUserInfo", obj); 
			System.out.println(results);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
