package cn.eainfo.system.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.system.model.SysMenuModel;
import cn.eainfo.system.po.SysMenu;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.service.SysMenuService;

import com.opensymphony.xwork2.ActionContext;

/**
 * 系统首页面处理，主要获取菜单，首页内容
 */
public class SysMainAction {

	// 菜单管理接口
	private SysMenuService sysMenuService;

	private long moduleId;// 菜单id

	private String menuData;// 菜单字符串

	private List placardList;
	private int placardCount;

	private String jsonString = null;// 返回结果

	/**
	 * 获取当前菜单的子菜单,非通用方法。菜单在建立时只能使用三级并且只能是三级。
	 * 
	 * @throws Exception
	 */
	public String execute() throws Exception {

		SysUser sysUser = (SysUser) ActionContext.getContext().getSession()
				.get("sysUser");
		SysMenuModel sysMenuModel = new SysMenuModel();
		sysMenuModel.setModuleParentId(moduleId);
		sysMenuModel.setUserId(sysUser.getUserId());
		List meunTopList = sysMenuService.getMenuChildByUserId(sysMenuModel);
		JSONArray jsonArray = new JSONArray();
		int topCount = meunTopList.size();
		for (int i = 0; i < topCount; i++) {
			SysMenu sysMenuTop = (SysMenu) meunTopList.get(i);
			long moduleId = sysMenuTop.getModuleId();// 菜单编号
			String moduleName = sysMenuTop.getModuleName();// 菜单名称
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("label", moduleName);
			sysMenuModel = new SysMenuModel();
			sysMenuModel.setModuleParentId(moduleId);
			sysMenuModel.setUserId(sysUser.getUserId());
			List meunSubList = sysMenuService
					.getMenuChildByUserId(sysMenuModel);
			JSONArray jsonSubArray = new JSONArray();
			int subCount = meunSubList.size();
			for (int j = 0; j < subCount; j++) {
				SysMenu sysMenuSub = (SysMenu) meunSubList.get(j);
				String moduleNameSub = sysMenuSub.getModuleName();// 菜单名称
				String moduleUri = sysMenuSub.getModuleUri();// 菜单地址
				moduleUri = "../" + moduleUri;// 加入服务器访问前缀
				JSONObject jsonSubObject = new JSONObject();
				jsonSubObject.element("label", moduleNameSub);
				jsonSubObject.element("url", moduleUri);
				jsonSubArray.add(jsonSubObject);
			}
			jsonObject.element("sub", jsonSubArray);
			jsonArray.add(jsonObject);
		}
		menuData = JSONObjectTool.getJson(jsonArray);
		return "menu";
	}

	/** 获取左侧菜单 */
	public String ajaxMenuList() throws Exception {

		SysUser sysUser = (SysUser) ActionContext.getContext().getSession().get("sysUser");
		SysMenuModel sysMenuModel = new SysMenuModel();
		sysMenuModel.setModuleParentId(moduleId);
		sysMenuModel.setUserId(sysUser.getUserId());
		List meunTopList = sysMenuService.getMenuChildByUserId(sysMenuModel);
		JSONArray jsonArray = new JSONArray();
		int topCount = meunTopList.size();
		for (int i = 0; i < topCount; i++) {
			SysMenu sysMenuTop = (SysMenu) meunTopList.get(i);
			long moduleId = sysMenuTop.getModuleId();// 菜单编号
			String moduleName = sysMenuTop.getModuleName();// 菜单名称
			JSONObject jsonObject = new JSONObject();
			jsonObject.element("menuId", moduleId);
			jsonObject.element("menuName", moduleName);
			jsonObject.element("moduleDesc", sysMenuTop.getModuleDesc());
			sysMenuModel = new SysMenuModel();
			sysMenuModel.setModuleParentId(moduleId);
			sysMenuModel.setUserId(sysUser.getUserId());
			List meunSubList = sysMenuService.getMenuChildByUserId(sysMenuModel);
			JSONArray jsonSubArray = new JSONArray();
			int subCount = meunSubList.size();
			for (int j = 0; j < subCount; j++) {
				SysMenu sysMenuSub = (SysMenu) meunSubList.get(j);
				String moduleNameSub = sysMenuSub.getModuleName();// 菜单名称
				String moduleUri = sysMenuSub.getModuleUri();// 菜单地址
				if (moduleUri != null && !"".equals(moduleUri)) {
					moduleUri = "../" + moduleUri;// 加入服务器访问前缀
				} else {
					moduleUri = "";// 加入服务器访问前缀
				}
				JSONObject jsonSubObject = new JSONObject();
				jsonSubObject.element("menuId", sysMenuSub.getModuleId());
				jsonSubObject.element("menuName", moduleNameSub);
				jsonSubObject.element("moduleDesc", sysMenuSub.getModuleDesc());
				jsonSubObject.element("target", "content");
				jsonSubObject.element("menuUrl", moduleUri);
				jsonSubArray.add(jsonSubObject);
			}
			jsonObject.element("subItem", jsonSubArray);
			jsonArray.add(jsonObject);
		}
		jsonString = jsonArray.toString();
		return "jsonString";
	}

	/**
	 * 转到首页
	 * */

	public String forwordContent() {

		return "forwordContent";
	}
	
	

	/**
	 * 显示广告
	 */
	public String placardContentShow() {

		return "placardContentShow";
	}

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public String getMenuData() {
		return menuData;
	}

	public void setMenuData(String menuData) {
		this.menuData = menuData;
	}

	public List getPlacardList() {
		return placardList;
	}

	public void setPlacardList(List placardList) {
		this.placardList = placardList;
	}

	public int getPlacardCount() {
		return placardCount;
	}

	public void setPlacardCount(int placardCount) {
		this.placardCount = placardCount;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

}
