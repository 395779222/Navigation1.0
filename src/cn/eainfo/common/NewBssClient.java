package cn.eainfo.common;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

public class NewBssClient {
	
	public String bssBusSev(String argXml,String areaCode) {
		Call call = null;
//		String url = "http://132.228.176.115:8080/CSB/webservices/CustomerService_A_LONGWAIT?wsdl";
		String url = "http://192.168.100.8:8080/CSB/webservices/CustomerService_A_LONGWAIT?wsdl";
		String ret="";
		try{
		 Service service = new Service();   
         call = (Call) service.createCall();   
         call.setTargetEndpointAddress(new java.net.URL(url)); 
         call.setUseSOAPAction(true);
         call.setOperationName("businessService");// 设置操作的名称。 查询接口queryService 。兑换业务一点通businessService
       SOAPHeaderElement header = new SOAPHeaderElement("","SRC_SYS_ID", "JS_POINT_USER");
       call.addHeader(header);
       SOAPHeaderElement header1 = new SOAPHeaderElement("","SRC_AUTH_PWD", "cElzqi2xkrq4C");
       call.addHeader(header1);
       SOAPHeaderElement header2 = new SOAPHeaderElement("","areacode", areaCode);
       call.addHeader(header2);
//       String argXml ="<orderInfo><order><orderTypeId>17</orderTypeId><prodSpecId>379</prodSpecId><accessNumber>"+phone+"</accessNumber>" +
//       		"<pricePlanPak><pricePlan><pricePlanCd>300509013541</pricePlanCd><actionType>0</actionType><startDt/>" +
//       		"<endDt></endDt></pricePlan></pricePlanPak></order></orderInfo>";
       ret = (String) call.invoke(new Object[] {argXml,areaCode,"-10024","-10024"});// 执行调用 businessService
       System.out.println("result===============>   "+ret);       
		}catch(Exception e){
			  e.printStackTrace();   
	     	  AxisFault fault = (AxisFault)e;
	          System.out.println("fault code:1111 " + fault.getFaultCode().toString());
		}
		return ret;
	}

	public String bssQuerySev(String url, String reqXml,String operation,String areaCode,int numType) {
		Call call = null;
		String ret="";
		try{
		 Service service = new Service();   
         call = (Call) service.createCall();   
         call.setTargetEndpointAddress(new java.net.URL(url)); 
         call.setUseSOAPAction(true);
         call.setOperationName(operation);// 设置操作的名称。 查询接口queryService 兑换业务一点通businessService
       SOAPHeaderElement header = new SOAPHeaderElement("","SRC_SYS_ID", "JS_POINT_USER");
       call.addHeader(header);
       SOAPHeaderElement header1 = new SOAPHeaderElement("","SRC_AUTH_PWD", "cElzqi2xkrq4C");
       call.addHeader(header1);
       SOAPHeaderElement header2 = new SOAPHeaderElement("","areacode", areaCode);
       call.addHeader(header2);
       ret= (String) call.invoke(new Object[] {reqXml,numType,areaCode,"-10024","-10024","1,2,3",2});// 执行调用 queryService     
       System.out.println("result===============>   "+ret);  
      
		}catch(Exception e){
			  e.printStackTrace();   
	     	  AxisFault fault = (AxisFault)e;
	          System.out.println("fault code:1111 " + fault.getFaultCode().toString());
		}
		return ret;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   
    	Call call = null;
        try  
        {   
            // 服务端的URL，需要根据情况更改 
 //       	String endpointURL = "http://132.228.103.4/CSB/webservices/CustomerService_A?wsdl";       
        	String endpointURL = "http://192.168.100.8:8080/CSB/webservices/CustomerService_A?wsdl";
 //       	String endpointURL = "http://192.168.100.8:8080/CSB/webservices/CustomerService_A_LONGWAIT?wsdl";
        			//"SRC_SYS_ID=DEFAULT_TEST&SRC_AUTH_PWD=123456";         	
            Service service = new Service();   
            call = (Call) service.createCall();   
            call.setTargetEndpointAddress(new java.net.URL(endpointURL)); 
//          call.setSOAPActionURI("http://tempuri.org/" + "resetPassword");
            call.setUseSOAPAction(true);
            call.setOperationName("queryService");// 设置操作的名称。 查询接口queryService
//            call.setOperationName("businessService");// 设置操作的名称。兑换业务一点通businessService
          SOAPHeaderElement header = new SOAPHeaderElement("","SRC_SYS_ID", "JS_POINT_USER");
          call.addHeader(header);
          SOAPHeaderElement header1 = new SOAPHeaderElement("","SRC_AUTH_PWD", "cElzqi2xkrq4C");
          call.addHeader(header1);
          SOAPHeaderElement header2 = new SOAPHeaderElement("","areacode", "0513");
          call.addHeader(header2);
          String ret = (String) call.invoke(new Object[] {"86591697",1,"0513","-10024","-10024","1,2,3",2});// 执行调用 queryService
//          String argXml ="<orderInfo><order><orderTypeId>17</orderTypeId><prodSpecId>379</prodSpecId><accessNumber>15305139265</accessNumber>" +
//          		"<pricePlanPak><pricePlan><pricePlanCd>300509013541</pricePlanCd><actionType>0</actionType><startDt/>" +
//          		"<endDt></endDt></pricePlan></pricePlanPak></order></orderInfo>";
//          String ret = (String) call.invoke(new Object[] {argXml,"0518","-10024","-10024"});// 执行调用 businessService
          System.out.println("result===============>   "+ret);   
    
   //         SRC_SYS_ID=&SRC_AUTH_PWD=&areacode=0510

//            xml = service.queryService(authenName, authenType.intValue(), areaCode, BssConstants.WORKER_POINT,
//                    BssConstants.CHANNEL_POINT, BssConstants.QUERY_TYPE_PRODUCT,  mode);//BssConstants.QUERY_MODE_ALL);
            
//            call.addParameter("accNbr", XMLType.XSD_STRING,ParameterMode.IN);
//            call.addParameter("accNbrType", XMLType.XSD_INTEGER,ParameterMode.IN);
//            call.addParameter("areaCode", XMLType.XSD_STRING,ParameterMode.IN);
//            call.addParameter("channelId", XMLType.XSD_STRING,ParameterMode.IN);
//            call.addParameter("staffCode", XMLType.XSD_STRING,ParameterMode.IN);
//            call.addParameter("queryType", XMLType.XSD_STRING,ParameterMode.IN);
//            call.addParameter("queryMode", XMLType.XSD_INTEGER,ParameterMode.IN);
           
            
    
            
           
            
        } catch (Exception e) {   
            e.printStackTrace();   
            
            //可以从AxisFault取出错误代码
            AxisFault fault = (AxisFault)e;
        	System.out.println("fault code:1111 " + fault.getFaultCode().toString());
        	
        	//也可以从SOAP头里面取出错误编码
//        	System.out.println("soap head:" + call.getResponseMessage().getSOAPHeader().toString());
//            Iterator iter = call.getResponseMessage().getSOAPHeader().getChildElements();
//            while(iter.hasNext()){
//            	SOAPElement soapE = (SOAPElement)iter.next();
//            	System.out.println(soapE.getNodeName());
//            	System.out.println(soapE.getValue());
//            }
//            System.out.println("fault code:2222 " + call.getResponseMessage().getSOAPBody().getFault().getFaultCode());
            
        }   
    

	}

}
