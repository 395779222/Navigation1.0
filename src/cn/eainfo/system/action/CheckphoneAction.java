package cn.eainfo.system.action;

import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import cn.eainfo.common.StringInfoCenter;
import cn.eainfo.system.model.CheckphoneModel;
import cn.eainfo.system.po.LoginLog;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.po.Checkphone;
import cn.eainfo.system.service.CheckphoneService;
import cn.eainfo.system.service.LoginLogService;

import com.crm.sendMessage.Jiangsu_Bestone;
import com.crm.sendMessage.Jiangsu_BestonePortType;
import com.crm.sendMessage.Jiangsu_Bestone_Impl;
import com.opensymphony.xwork2.ActionContext;

public class CheckphoneAction {

	private String message;//保存返回的信息
	private String actionName;//获取页面的重新获取或者确定按钮的时候
	private String checkphonepwd;//输入的验证码
	
	private CheckphoneService checkphoneSerivce;
	private LoginLogService loginLogService;
	
	public String checkPhone() throws Exception{
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		// 生成动态码
	    Date date = new Date();
		String accountId = sysUser.getTelephone();// 电话号码
		if(accountId==null){
			message = "获取失败，您未登记电话号码！";
			return "success";
		}
		String checkpwd = StringInfoCenter.createDynamicpwd();
		String id = StringInfoCenter.createId();
		String content = "password:" + checkpwd + "";
	//	String content = "您好，您本次登录的鉴权码为:" + checkpwd + "，本鉴权码限有效期1小时，成功输入后自动失效。";
		if (actionName==null||actionName.equals("chongxin") || actionName.equals("")) {
			String url = "http://132.228.133.27/webservice/jshb118114_sms_service_v1.php?wsdl";
//			String url ="http://192.168.100.8:8888/webservice/jshb118114_sms_service_v1.php?wsdl";
			// 调用发送短信接口
			Jiangsu_Bestone bestoneservice = new Jiangsu_Bestone_Impl(url);
			Jiangsu_BestonePortType bestoneportype = bestoneservice.getjiangsu_bestonePortType();
			String messagetemp = bestoneportype.sendMessage("jshb_authen", id,accountId, content);
			// 解析短信系统的返回结果
			try {
				Document document = DocumentHelper.parseText(messagetemp);
				Node node = document.selectSingleNode("/return/status");
				String status = node.getText();
				if (status != null && "0".equals(status)){
//				if(true){
					Checkphone checkphone = new Checkphone();
					checkphone.setId(StringInfoCenter.createId());
					checkphone.setAccountid(accountId);
					checkphone.setCrrstate(new Long(1));
					checkphone.setCheckphonepwd(checkpwd);
					checkphone.setSendmessage(content);
					checkphone.setSendstate(new Long(1));
					checkphone.setSendtime(date);
					checkphoneSerivce.insertCheckphone(checkphone);
					//成功转到鉴权页面
					return "success";
				}
				//失败提示用户重新获取
				message="获取失败，请点击重新获取！";
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (actionName.equals("queren")) {
			// 验证用户输入鉴权码
			CheckphoneModel checkphoneModel = new CheckphoneModel();
			checkphoneModel.setAccountid(accountId);
			checkphoneModel.setCrrstate(new Long(1));
			Checkphone checkphonechek = checkphoneSerivce.getCheckphoneBean(checkphoneModel);
			if (checkphonechek != null&&checkphonechek.getCheckphonepwd().equals(checkphonepwd)) {
				checkphonechek.setCrrstate(new Long(2));
				checkphoneSerivce.updateCheckphone(checkphonechek);
				//正确转到系统首页
				LoginLog loginLog = new LoginLog();
				loginLog.setCreatetime(new Date());
				loginLog.setOperator(sysUser.getLoginName());
				loginLog.setOperatid(sysUser.getUserId());
				String hostIp = "";
				try {
					java.net.InetAddress inet = java.net.InetAddress.getLocalHost();
				    hostIp = inet.getHostAddress();
				} catch (Exception e) {
					message =  "获取IP失败，请联系管理员！";
					return "success";
				} 
				loginLog.setIp(hostIp);
				loginLogService.insertLoginLog(loginLog);
				return "logSuccess";
			}
			//错误弹出错误信息用户重新输入
			message =  "鉴权码错误，请重新输入！";
			return "success";
		}
		return "input";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public CheckphoneService getCheckphoneSerivce() {
		return checkphoneSerivce;
	}

	public void setCheckphoneSerivce(CheckphoneService checkphoneSerivce) {
		this.checkphoneSerivce = checkphoneSerivce;
	}

	public String getCheckphonepwd() {
		return checkphonepwd;
	}

	public void setCheckphonepwd(String checkphonepwd) {
		this.checkphonepwd = checkphonepwd;
	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	
}
