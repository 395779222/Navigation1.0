package cn.eainfo.system.action;

import java.io.InputStream;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.system.po.SysMenu;
import cn.eainfo.system.service.SysMenuService;

/**
 * 菜单管理menuz
 */
public class SysMenuAction {
	// 菜单管理接口
	private SysMenuService sysMenuService;

	/**
	 * 列表查询参数，page当前页码 rows是当前页的行数
	 */

	private InputStream operateResultString = null;// 返回结果

	// 新增/修改参数
	private SysMenu sysMenu;
	private long moduleId;// 菜单编号
	private long moduleParentId;// 父级菜单编号
	private int moduleFolder;// 是否目录
	private String moduleName;// 菜单名称
	private String moduleDesc;// 菜单描述
	private String moduleUri;// 菜单地址
	private int orderNumber;// 排序
	private long roleId;
	private String menuString;// 上级菜单json
	private List menuList;
	private long[] moduleIds;
	private int[] orderNumbers;

	private String jsonString;

	public String forwordList() {
		menuList = sysMenuService.getBySeparatorSysMenu(0);
		String menu = "<select id='moduleParentId' name='moduleParentId' editable='false' class='easyui-combobox'><option value='0'>顶级目录</option>";
		for (int i = 0; i < menuList.size(); i++) {
			SysMenu sysMenu = (SysMenu) menuList.get(i);
			menuString = menuString + "<option value='" + sysMenu.getModuleId()
					+ "'>" + sysMenu.getModuleName() + "</option>";
		}
		menuString = menu + menuString + "</select>";
		return "forwordList";
	}

	/**
	 * 列表查询
	 * 
	 * @throws Exception
	 */
	public String ajaxList() throws Exception {
		List list = sysMenuService.getBySeparatorSysMenu(0);
		AjaxJsonListData ajaxJsonListData = new AjaxJsonListData();
		ajaxJsonListData.setTotal(list.size());
		ajaxJsonListData.setRows(list);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}

	/**
	 * 修改
	 * 
	 * @throws Exception
	 */
	public String ajaxSaveOrUpdate() throws Exception {
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		SysMenu sm = sysMenuService.getSysMenubyName(moduleName);
		if (moduleId != 0) {
			// 修改
			sysMenu = sysMenuService.getSysMenuBean(moduleId);
			sysMenu.setModuleDesc(moduleDesc);
			
			sysMenu.setModuleFolder(moduleFolder);
			if(moduleFolder==1){
				sysMenu.setModuleUri("");
			}else{
				sysMenu.setModuleUri(moduleUri);
			}
			sysMenu.setOrderNumber(orderNumber);
			sysMenu.setModuleParentId(moduleParentId);			
			if(moduleName.equals(sysMenu.getModuleName())){
				sysMenu.setModuleName(moduleName);
				sysMenuService.updateSysMenu(sysMenu);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
			}else{
				if(sm==null){
					sysMenu.setModuleName(moduleName);
					sysMenuService.updateSysMenu(sysMenu);
					ajaxBeanData.setResult("sucess");
					ajaxBeanData.setResume("成功");
				}else{
					ajaxBeanData.setResult("faild");
					ajaxBeanData.setResume("当前名称已经存在，请重新输入！");
				}
			}
		} else {
			if(sm==null){
				// 新增
				sysMenu = new SysMenu();
				sysMenu.setModuleDesc(moduleDesc);
				sysMenu.setModuleFolder(moduleFolder);
				sysMenu.setModuleName(moduleName);
				sysMenu.setModuleParentId(moduleParentId);
				if(moduleFolder==1){
					sysMenu.setModuleUri("");
				}else{
					sysMenu.setModuleUri(moduleUri);
				}
				sysMenu.setOrderNumber(orderNumber);
				sysMenuService.insertSysMenu(sysMenu);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
			}else{
				ajaxBeanData.setResult("faild");
				ajaxBeanData.setResume("当前名称已经存在，请重新输入！");
			}
		}
		ajaxBeanData.setLabel("sysMenu");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";

	}

	/**
	 * 转到新增菜单
	 * 
	 * @throws Exception
	 */
	public String forwordInert() throws Exception {
		List separatorList = sysMenuService.getBySeparatorSysMenu(0);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put(0, );
		jsonObject.element("value", 0);
		jsonObject.element("text", "根菜单");
		jsonArray.add(jsonObject);
		int count = separatorList.size();
		for (int i = 0; i < count; i++) {
			SysMenu sysMenu = (SysMenu) separatorList.get(i);
			long moduleId = sysMenu.getModuleId();
			String moduleName = sysMenu.getModuleName();
			jsonObject = new JSONObject();
			// jsonObject.put(moduleId, moduleName);
			jsonObject.element("value", moduleId);
			jsonObject.element("text", moduleName);
			jsonArray.add(jsonObject);
		}
		menuString = JSONObjectTool.getJson(jsonArray);
		return "forwordInert";
	}

	/**
	 * 新增菜单
	 * 
	 * @throws Exception
	 */
	public String ajaxInert() throws Exception {
		sysMenu = new SysMenu();
		sysMenu.setModuleDesc(moduleDesc);
		sysMenu.setModuleFolder(moduleFolder);
		sysMenu.setModuleName(moduleName);
		sysMenu.setModuleParentId(moduleParentId);
		sysMenu.setModuleUri(moduleUri);
		sysMenu.setOrderNumber(0);
		sysMenuService.insertSysMenu(sysMenu);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("SysUser");
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	/**
	 * 获取菜单
	 * 
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		sysMenu = sysMenuService.getSysMenuBean(moduleId);
		moduleParentId = sysMenu.getModuleParentId();
		if (moduleParentId == 0) {
			sysMenu.setModuleParentName("根菜单");
		} else {
			SysMenu fSysMenu = sysMenuService.getSysMenuBean(moduleParentId);
			sysMenu.setModuleParentName(fSysMenu.getModuleName());
		}

		long moduleFolder = sysMenu.getModuleFolder();
		if (moduleFolder == 1) {
			sysMenu.setModuleFoldername("目录");
		} else {
			sysMenu.setModuleFoldername("菜单");
		}
		jsonString = JSONObjectTool.getObjectJson(sysMenu);
		return "jsonString";
	}

	/**
	 * 转到修改菜单
	 * 
	 * @throws Exception
	 */
	public String forwordUpdate() throws Exception {
		return "forwordUpdate";
	}

	/**
	 * 修改菜单
	 * 
	 * @throws Exception
	 * 
	 */
	public String ajaxUpdate() throws Exception {
		sysMenu = sysMenuService.getSysMenuBean(moduleId);
		sysMenu.setModuleDesc(moduleDesc);
		sysMenu.setModuleName(moduleName);
		sysMenu.setModuleUri(moduleUri);
		sysMenuService.updateSysMenu(sysMenu);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("SysUser");
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	public String forwordShow() {
		return "forwordShow";
	}

	/**
	 * 删除菜单
	 * 
	 * @throws Exception
	 * 
	 */
	public String ajaxDelete() throws Exception {
		sysMenuService.deleteSysMenu(moduleId);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysMenu");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}

	public String forwordOrder() {

		return "forwordOrder";
	}

	

	public String ajaxOrder() throws Exception {
		if (moduleIds != null && moduleIds.length > 0) {
			int count = moduleIds.length;
			for (int i = 0; i < count; i++) {
				sysMenu = sysMenuService.getSysMenuBean(moduleIds[i]);
				sysMenu.setOrderNumber(orderNumbers[i]);
				sysMenuService.updateSysMenu(sysMenu);
			}

		}

		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("SysMenu");
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}

	public List getMenuList() {
		return menuList;
	}

	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	public InputStream getOperateResultString() {
		return operateResultString;
	}

	public void setOperateResultString(InputStream operateResultString) {
		this.operateResultString = operateResultString;
	}

	public long[] getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(long[] moduleIds) {
		this.moduleIds = moduleIds;
	}

	public int[] getOrderNumbers() {
		return orderNumbers;
	}

	public void setOrderNumbers(int[] orderNumbers) {
		this.orderNumbers = orderNumbers;
	}

	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public long getModuleId() {
		return moduleId;
	}

	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}

	public long getModuleParentId() {
		return moduleParentId;
	}

	public void setModuleParentId(long moduleParentId) {
		this.moduleParentId = moduleParentId;
	}

	public int getModuleFolder() {
		return moduleFolder;
	}

	public void setModuleFolder(int moduleFolder) {
		this.moduleFolder = moduleFolder;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getModuleUri() {
		return moduleUri;
	}

	public void setModuleUri(String moduleUri) {
		this.moduleUri = moduleUri;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMenuString() {
		return menuString;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
