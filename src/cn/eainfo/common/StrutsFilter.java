package cn.eainfo.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

/**
 * 过滤系统中不需要struts2处理的路径
 */
public class StrutsFilter extends StrutsPrepareAndExecuteFilter {
	private FilterConfig config;

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String name = config.getInitParameter("urlpath");
		String crrUrl = request.getRequestURL().toString();
		if (crrUrl.indexOf(name) > -1) {
			// System.out.println("使用自定义的过滤器");
			arg2.doFilter(arg0, arg1);
		} else {
			// System.out.println("使用默认的过滤器");
			super.doFilter(arg0, arg1, arg2);
		}

	}

	public FilterConfig getConfig() {
		return config;
	}

	public void setConfig(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
		super.init(arg0);
	}

}
