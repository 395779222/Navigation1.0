package cn.eainfo.common;

/**
 * 系统自定义拦截器，进行错误处理使用
 * 金贤敏  2010-04-02
 * 版本 1.0
 */
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class BeforeResultInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionProxy proxy = invocation.getProxy();
		String methodName = proxy.getMethod();
		String result = "";
		try {
			result = invocation.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			String errorReturn = "系统出现故障,请联系管理员。";
			invocation.getInvocationContext().getValueStack().set("error",
					errorReturn);
			if (methodName != null && methodName.startsWith("ajax")) {
				result = "ajaxError";
			} else {
				result = "error";
			}
		}
		return result;
	}

}
