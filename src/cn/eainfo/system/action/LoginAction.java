package cn.eainfo.system.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.eainfo.system.model.SysMenuModel;
import cn.eainfo.system.po.LoginLog;
import cn.eainfo.system.po.SysMenu;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.LoginLogService;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysMenuService;
import cn.eainfo.system.service.SysUserService;

import com.ddgx.security.MessageDigest;
import com.opensymphony.xwork2.ActionContext;

/**
 * 登录
 * 
 * 
 */
public class LoginAction {
	// 用户管理接口
	private SysUserService sysUserService;
	// 菜单管理接口
	private SysMenuService sysMenuService;
	// 地区管理接口
	private SysAreaService sysAreaService;

	private LoginLogService loginLogService;

	// private CustomerService customerService;
	// private BussinessService bussinessService;

	// 登录参数
	private String username;
	private String password;
	private String showCode;
	private String userError = "";

	public String execute() {
		showCode = showCode.toUpperCase();
		String rand = (String) ActionContext.getContext().getSession()
				.get("imgValidate");
		if (rand != null && rand.equals(showCode)) {
			SysUser sysUser = sysUserService.userLongin(username,
					this.encryptPassword(password));
			if (sysUser != null) {
				int status = sysUser.getStatus();
				if (status == 1) {	
					long userId = sysUser.getUserId();
					// 正常状态
					ActionContext.getContext().getSession()
							.put("sysUser", sysUser);
					SysMenuModel sysMenuModel = new SysMenuModel();
					sysMenuModel.setModuleParentId(0);
					sysMenuModel.setUserId(userId);
					List meunTopList = sysMenuService
							.getMenuChildByUserId(sysMenuModel);
					JSONArray meunTop = new JSONArray();
					if (meunTopList != null && meunTopList.size() > 0) {
						int count = meunTopList.size();
						for (int i = 0; i < count; i++) {
							SysMenu sysMenu = (SysMenu) meunTopList
									.get(i);
							JSONObject menuObject = new JSONObject();
							menuObject.put("menuId",
									sysMenu.getModuleId());
							menuObject.put("menuName",
									sysMenu.getModuleName());
							menuObject.put("moduleDesc",
									sysMenu.getModuleDesc());
							meunTop.add(menuObject);
						}
					}
					ActionContext.getContext().getSession()
							.put("meunTop", meunTop.toString());
					ActionContext.getContext().getSession()
							.put("username", username);
					ActionContext.getContext().getSession()
							.put("areaNo", sysUser.getAreaNo());
					return "login";
				} else {
					// 注销状态
					userError = "该帐号已注销，请联系管理员！";
					return "input";
				}

			} else {
				userError = "输入的信息有误，请核实！";
				return "input";
			}
		} else {
			userError = "输入验证码有误，请核实！";
			return "input";
		}

	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getShowCode() {
		return showCode;
	}

	public void setShowCode(String showCode) {
		this.showCode = showCode;
	}

	public String getUserError() {
		return userError;
	}

	public void setUserError(String userError) {
		this.userError = userError;
	}

	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public LoginLogService getLoginLogService() {
		return loginLogService;
	}

	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}

	/**
	 * 
	 * @Title: encryptPassword
	 * @Description:
	 * @param @param password
	 * @param @return
	 * @return String
	 * @throws
	 * @author Zhousu
	 */
	public static final String encryptPassword(String password) {
		char[] ELEMENTS_ENCRYPT = { '0', '3', '6', '9', '2', '5', '8', '1',
				'4', '7', 'A', 'F', 'K', 'P', 'U', 'Z' };
		return MessageDigest.encrypt(password, "md5", ELEMENTS_ENCRYPT);
	}

}
