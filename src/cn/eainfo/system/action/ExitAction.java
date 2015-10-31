package cn.eainfo.system.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.Action;

public class ExitAction implements Action, ServletRequestAware {

	private HttpServletRequest request;

	public String execute() {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "loginOut";
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
