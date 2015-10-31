package cn.eainfo.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.eainfo.system.po.SysUser;

public class UserFilter implements Filter {
	private FilterConfig filterConfig;

	private String noSessionError = "timeout.jsp";
	private String ajaxNoSessionError = "ajaxTimeout.jsp";

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(true);
		SysUser sysUser = (SysUser) session.getAttribute("sysUser");

		// 当前请求路径
		String url = request.getRequestURI();
		// 当前的请求功能
		String actionCode = "";
		// 当前的请求模块
		String moduleName = "";
		// 根据url 取模块url 及功能代码
		if (url.indexOf("!") != -1) {
			moduleName = url.substring(url.lastIndexOf("/") + 1, url
					.indexOf("!"));
			if (url.indexOf("?") != -1) {
				actionCode = url.substring(url.lastIndexOf("!") + 1, url
						.indexOf("?"));
			} else {
				actionCode = url.substring(url.lastIndexOf("!") + 1, url
						.length());
			}
		}

		if (sysUser != null) {
			arg2.doFilter(request, response);
		} else {
			if (actionCode != null && !"".equals(actionCode)
					&& actionCode.startsWith("ajax")) {
				response.sendRedirect(request.getContextPath() + "/"
						+ ajaxNoSessionError);
			} else {
				response.sendRedirect(request.getContextPath() + "/"
						+ noSessionError);
			}

		}
	}

	public void destroy() {
		filterConfig = null;
	}

	public void init(FilterConfig arg0) throws ServletException {
		filterConfig = arg0;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public String getNoSessionError() {
		return noSessionError;
	}

	public void setNoSessionError(String noSessionError) {
		this.noSessionError = noSessionError;
	}

	public String getAjaxNoSessionError() {
		return ajaxNoSessionError;
	}

	public void setAjaxNoSessionError(String ajaxNoSessionError) {
		this.ajaxNoSessionError = ajaxNoSessionError;
	}

}
