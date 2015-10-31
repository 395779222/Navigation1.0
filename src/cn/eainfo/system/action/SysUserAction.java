package cn.eainfo.system.action;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.eainfo.common.AjaxBeanData;
import cn.eainfo.common.AjaxJsonListData;
import cn.eainfo.common.JSONObjectTool;
import cn.eainfo.system.model.SysAreaModel;
import cn.eainfo.system.model.SysUserModel;
import cn.eainfo.system.po.SysArea;
import cn.eainfo.system.po.SysRole;
import cn.eainfo.system.po.SysUser;
import cn.eainfo.system.po.SysUserRoleRel;
import cn.eainfo.system.service.SysAreaService;
import cn.eainfo.system.service.SysRoleService;
import cn.eainfo.system.service.SysUserService;

import com.ddgx.security.MessageDigest;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户管理
 */
public class SysUserAction {
	// 用户管理接口
	private SysUserService sysUserService;
	// 角色管理接口
	private SysRoleService sysRoleService;
	private SysAreaService sysAreaService;

	/**
	 * 列表查询参数，page当前页码 rows是当前页的行数
	 */

	private InputStream operateResultString = null;// 返回结果
	private String msg;
	// 查询参数
	private String userNameSelect = "";
	private String loginNameSelect = "";
	private String telephoneSelect = "";

	// 新增/修改参数
	private SysUser sysUser;
	private long userId;// 用户编号
	private String userName;// 用户名称
	private String loginName;// 登录帐号
	private String password;// 登录密码 md5加密

	private String telephone;// 联系电话
	private String address;// 地址
	private int status;// 用户当前状态 1.正常 2.注销
	private String department;//部门
	private String phone;//固话
	private String email;//邮箱
	private String identifier;//
	private String menuselected;
	private Date updatetime;//修改时间
	private String remark;//备注
	private String ip;//ip
	private Date createTime;//创建时间
	private String openOperator;
	private Date expireTime;
	private Date effectTime;
	private Date loginBeginTime;
	private Date loginEndTime;
	private Integer maxOnline;
	private String adminType;
	private String shenDate;
	private String shiDate;
	private String logDate;
	private String endDate;
	
	private String roleSelect = "";// 角色选择框

	private long[] roleIds;// 角色选择菜单
	
	private String areaNo;
	private String name;
	private String areaCode;
	
	private String areaMenu;
	
	private String jsonString;
	private long rows;//每页显示的记录数
	private long page;//当前第几页 
	private List areaList;
	private String roleString;//获取 角色
	private String areaString;
	private String areaStrings;
	private String oldpassword;
	
	public String forwordList() throws Exception{
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		sm.setAreaNo(sysUser.getAreaNo());
		areaList = sysAreaService.getSysAreaList(sm);
		String menu = "<select id='areaNo' name='areaNo' class='easyui-combobox' editable='false'><option value=''>请选择</option>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaString = areaString + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		areaString = menu + areaString + "</select>";
		
		String menus = "<select id='areaNos' name='areaNos' class='easyui-combobox' editable='false'><option value=''>请选择</option>";
		for (int i = 0; i < areaList.size(); i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaStrings = areaStrings + "<option value='"+sysArea.getAreaNo()+"'>"+sysArea.getName()+"</option>";
		}
		areaStrings = menus + areaStrings + "</select>";
		List roleLists = sysRoleService.getAll();
		int count = roleLists.size();
		for (int i = 0; i < count; i++) {
			SysRole sysRole = (SysRole) roleLists.get(i);
			long roleId = sysRole.getRoleId();// 角色编号
			String roleName = sysRole.getRoleName();// 角色名称
			roleSelect = roleSelect + "<input type='checkBox' name='roleIds' value='" 
			+ roleId + "'>" + roleName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			if ((i+1)%4==0) {
				roleSelect = roleSelect + "<br/>";
			}
		}
		roleSelect = roleSelect+"<br/><br/>";
		return "forwordList";
	}
	/**
	 * 列表查询
	 * 
	 * @throws Exception
	 */
	public String ajaxList() throws Exception {
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysUserModel sysUserModel = new SysUserModel();
		sysUserModel.setUserName(userNameSelect);
		sysUserModel.setLoginName(loginNameSelect);
		sysUserModel.setTelephone(telephoneSelect);
		sysUserModel.setStatus(status);
		sysUserModel.setPage(page);
		sysUserModel.setPageSize(rows);
		sysUserModel.setAreaNo(sysUser.getAreaNo());
		sysUserModel.setAreaCode(areaNo);
		AjaxJsonListData ajaxJsonListData =  sysUserService.getSysUserPage(sysUserModel);
		jsonString = JSONObjectTool.getObjectJson(ajaxJsonListData);
		return "jsonString";
	}

	/**
	 * 转到新增用户
	 * 
	 * @throws Exception
	 */
	public String forwordInert() throws Exception {
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		sm.setAreaNo(sysUser.getAreaNo());
		List areaList = sysAreaService.getSysAreaList(sm);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put(0, );
		jsonObject.element("value", "");
		jsonObject.element("text", "请选择");
		jsonArray.add(jsonObject);
		int countArea = areaList.size();
		for (int i = 0; i < countArea; i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaNo = sysArea.getAreaNo();
//			long moduleId = sysMenu.getModuleId();
			name = sysArea.getName();
			jsonObject = new JSONObject();
			// jsonObject.put(moduleId, moduleName);
			jsonObject.element("value", areaNo);
			jsonObject.element("text", name);
			jsonArray.add(jsonObject);
		}
		areaMenu = JSONObjectTool.getJson(jsonArray);
		
		
		
		List roleList = sysRoleService.getAll();
		int roleNumber = 0;
		roleSelect = roleSelect
				+ "<table  border=0 cellSpacing=0 cellpadding=0 style='width:100%;border-collapse:collapse;background:#f6f6f6'>";
		int count = roleList.size();
		for (int i = 0; i < count; i++) {
			SysRole sysRole = (SysRole) roleList.get(i);
			long roleId = sysRole.getRoleId();// 角色编号
			String roleName = sysRole.getRoleName();// 角色名称

			if (i % 6 == 0) {
				roleNumber = i;
				roleSelect = roleSelect + "<tr>";
			}
			if (i - 6 == roleNumber) {
				roleSelect = roleSelect + "</tr>";
			}

			if (i == count - 1 && count % 6 != 0) {
				roleSelect = roleSelect
						+ "<td style='border:1px solid #b4defc' colspan='"
						+ (6 - (i % 6))
						+ "'><input type='checkBox' name='roleIds' onclick='checkRoleSelect()' value='"
						+ roleId + "'>" + roleName + "</td>";
				roleSelect = roleSelect + "</tr>";
			} else {
				roleSelect = roleSelect
						+ "<td style='border:1px solid #b4defc'><input type='checkBox' name='roleIds' onclick='checkRoleSelect()' value='"
						+ roleId + "'>" + roleName + "</td>";
			}
		}
		roleSelect = roleSelect + "</table>";

		return "forwordInert";
	}
	/**
	 * 修改或者新增
	 * 
	 * @throws Exception
	 */
	public String ajaxSaveOrUpdate() throws Exception {
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 将接收到的字符串转化为Date类型
		if (userId != 0) {
			// 修改
			sysUser = sysUserService.getSysUserBean(userId);
			sysUser.setAreaNo(areaNo);
			sysUser.setAddress(address);
			sysUser.setUserName(userName);
			sysUser.setStatus(status);
			sysUser.setDepartment(department);
			sysUser.setPhone(phone);
			sysUser.setIdentifier(identifier);
			sysUser.setEmail(email);
			sysUser.setShenDate(shenDate);
			if(shenDate!=null&&!"".equals(shenDate)){
				sysUser.setEffectTime(df.parse(shenDate));
			}
			if(shiDate!=null&&!"".equals(shiDate)){
				sysUser.setExpireTime(df.parse(shiDate));
			}
			if(logDate!=null&&!"".equals(logDate)){
				sysUser.setLoginBeginTime(df.parse(logDate));
			}
			if(endDate!=null&&!"".equals(endDate)){
				sysUser.setLoginEndTime(df.parse(endDate));
			}			
			sysUser.setAdminType(adminType);
			sysUser.setIp(ip);
			sysUser.setRemark(remark);
			sysUser.setMenuselected("N");
			sysUser.setAreaLevel(2);
			sysUser.setUpdatetime(new Date());
			if (password != null && !"".equals(password)) {
				sysUser.setPassword(this.encryptPassword(password));
			}
			sysUser.setTelephone(telephone);
			if(loginName.equals(sysUser.getLoginName())){
				sysUser.setLoginName(loginName);
				sysUserService.updateSysUser(sysUser, roleIds);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
			}else{
				List tempList = sysUserService.checkUser(loginName);
				if (tempList == null || tempList.size() == 0) {
					sysUser.setLoginName(loginName);
					sysUserService.updateSysUser(sysUser, roleIds);
					ajaxBeanData.setResult("sucess");
					ajaxBeanData.setResume("成功");
				}else{
					ajaxBeanData.setResult("faild");
					ajaxBeanData.setResume("当前登录帐号：" + loginName + "已经存在！");
				}
			}
			
		} else {
			List tempList = sysUserService.checkUser(loginName);
			if (tempList == null || tempList.size() == 0) {
				// 新增
				sysUser = new SysUser();
				sysUser.setAddress(address);
				sysUser.setUserName(userName);
				sysUser.setLoginName(loginName);
				sysUser.setDepartment(department);
				sysUser.setPhone(phone);
				sysUser.setIdentifier(identifier);
				sysUser.setEmail(email);				
				if(shenDate!=null&&!"".equals(shenDate)){
					sysUser.setEffectTime(df.parse(shenDate));
				}
				if(shiDate!=null&&!"".equals(shiDate)){
					sysUser.setExpireTime(df.parse(shiDate));
				}
				if(logDate!=null&&!"".equals(logDate)){
					sysUser.setLoginBeginTime(df.parse(logDate));
				}
				if(endDate!=null&&!"".equals(endDate)){
					sysUser.setLoginEndTime(df.parse(endDate));
				}
				sysUser.setAdminType(adminType);
				sysUser.setMenuselected("N");
				sysUser.setAreaLevel(2);
				sysUser.setIp(ip);
				sysUser.setRemark(remark);
				sysUser.setCreateTime(new Date());
				if (password != null && !"".equals(password)) {
					sysUser.setPassword(this.encryptPassword(password));
				} else {
					sysUser.setPassword(this.encryptPassword("123456"));
				}
				sysUser.setAreaNo(areaNo);
				sysUser.setStatus(1);
				sysUser.setTelephone(telephone);
				sysUserService.insertSysUser(sysUser, roleIds);
				ajaxBeanData.setResult("sucess");
				ajaxBeanData.setResume("成功");
			} else {
				ajaxBeanData.setResult("faild");
				ajaxBeanData.setResume("当前登录帐号：" + loginName + "已经存在！");
				
			}
		}
		ajaxBeanData.setLabel("SysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";

	}
	
	/**
	 * 新增用户
	 * 
	 * @throws Exception
	 */
	public String ajaxInert() throws Exception {
		sysUser = new SysUser();
		sysUser.setAddress(address);

		sysUser.setUserName(userName);
		sysUser.setLoginName(loginName);
		if (password != null && !"".equals(password)) {
			sysUser.setPassword(this.encryptPassword(password));
		} else {
			sysUser.setPassword(this.encryptPassword("123456"));
		}
		sysUser.setAreaNo(areaNo);
		sysUser.setStatus(1);
		sysUser.setTelephone(telephone);
		sysUserService.insertSysUser(sysUser, roleIds);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("SysUser");
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	/**
	 * 获取用户
	 * List selectRole = sysUserService.getRoleByUserId(userId);
	 * @throws Exception
	 */
	public String ajaxGetBean() throws Exception {
		sysUser = sysUserService.getSysUserBean(userId);
		List selectRole = sysUserService.getRoleByUserId(sysUser.getUserId());
		List roleList = sysRoleService.getAll();
		for (int i = 0; i < roleList.size(); i++) {
			SysRole sysRole = (SysRole) roleList.get(i);
			roleSelect = roleSelect + "<input type='checkBox'";
			for(int j=0;j<selectRole.size();j++){
				SysUserRoleRel sysUserRoleRel = (SysUserRoleRel) selectRole.get(j);
			    if(sysRole.getRoleId()==sysUserRoleRel.getRoleId()){
			    	roleSelect = roleSelect + "checked";
			    }
			}
			roleSelect= roleSelect+" name='roleIds' value='" 
					+ sysRole.getRoleId() + "'>" + sysRole.getRoleName()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			
			if ((i+1)%4==0) {
				roleSelect = roleSelect + "<br/>";
			}
		}
		roleSelect = roleSelect+"<br/><br/>";
		sysUser.setRoleString(roleSelect);
		sysUser.setPassword("");
		jsonString = JSONObjectTool.getObjectJson(sysUser);
		return "jsonString";
	}

	/**
	 * 转到修改用户
	 * 
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	public String forwordUpdate() throws Exception {
		SysUser sysUser = (SysUser)ActionContext.getContext().getSession().get("sysUser");
		SysAreaModel sm = new SysAreaModel();
		sm.setAreaNo(sysUser.getAreaNo());
		List areaList = sysAreaService.getSysAreaList(sm);
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		// jsonObject.put(0, );
		jsonObject.element("value", "");
		jsonObject.element("text", "请选择");
		jsonArray.add(jsonObject);
		int countArea = areaList.size();
		for (int i = 0; i < countArea; i++) {
			SysArea sysArea = (SysArea) areaList.get(i);
			areaNo = sysArea.getAreaNo();
//			long moduleId = sysMenu.getModuleId();
			name = sysArea.getName();
			jsonObject = new JSONObject();
			// jsonObject.put(moduleId, moduleName);
			jsonObject.element("value", areaNo);
			jsonObject.element("text", name);
			jsonArray.add(jsonObject);
		}
		areaMenu = JSONObjectTool.getJson(jsonArray);
		
		String selectRoleId = "";
		List selectRole = sysUserService.getRoleByUserId(userId);
		int selectCount = selectRole.size();
		for (int i = 0; i < selectCount; i++) {
			if (i == 0) {
				selectRoleId = selectRoleId + ",";
			}
			SysUserRoleRel sysUserRoleRel = (SysUserRoleRel) selectRole.get(i);
			selectRoleId = selectRoleId + sysUserRoleRel.getRoleId() + ",";
		}

		List roleList = sysRoleService.getAll();
		int roleNumber = 0;
		roleSelect = roleSelect
				+ "<table  border=0 cellSpacing=0 cellpadding=0 style='width:100%;border-collapse:collapse;background:#f6f6f6'>";
		int count = roleList.size();
		for (int i = 0; i < count; i++) {
			SysRole sysRole = (SysRole) roleList.get(i);
			long roleId = sysRole.getRoleId();// 角色编号
			String roleName = sysRole.getRoleName();// 角色名称

			if (i % 6 == 0) {
				roleNumber = i;
				roleSelect = roleSelect + "<tr>";
			}
			if (i - 6 == roleNumber) {
				roleSelect = roleSelect + "</tr>";
			}

			if (i == count - 1 && count % 6 != 0) {
				if (selectRoleId.indexOf("," + roleId + ",") > -1) {
					roleSelect = roleSelect
							+ "<td style='border:1px solid #b4defc' colspan='"
							+ (6 - (i % 6))
							+ "'><input type='checkBox' name='roleIds' onclick='checkRoleSelect()' checked='checked' value='"
							+ roleId + "'>" + roleName + "</td>";
				} else {
					roleSelect = roleSelect
							+ "<td style='border:1px solid #b4defc' colspan='"
							+ (6 - (i % 6))
							+ "'><input type='checkBox' name='roleIds' onclick='checkRoleSelect()' value='"
							+ roleId + "'>" + roleName + "</td>";
				}
				roleSelect = roleSelect + "</tr>";
			} else {
				if (selectRoleId.indexOf("," + roleId + ",") > -1) {
					roleSelect = roleSelect
							+ "<td style='border:1px solid #b4defc'><input type='checkBox' checked='checked' name='roleIds' onclick='checkRoleSelect()' value='"
							+ roleId + "'>" + roleName + "</td>";
				} else {
					roleSelect = roleSelect
							+ "<td style='border:1px solid #b4defc'><input type='checkBox'  onclick='checkRoleSelect()'  name='roleIds' value='"
							+ roleId + "'>" + roleName + "</td>";
				}
			}
		}
		roleSelect = roleSelect + "</table>";

		return "forwordUpdate";
	}

	/**
	 * 修改用户
	 * 
	 * @throws Exception
	 */
	public String ajaxUpdate() throws Exception {
		sysUser = sysUserService.getSysUserBean(userId);
		sysUser.setAreaNo(areaNo);
		sysUser.setAddress(address);
		sysUser.setUserName(userName);
		if (password != null && !"".equals(password)) {
			sysUser.setPassword(this.encryptPassword(password));
		}
		sysUser.setTelephone(telephone);
		sysUserService.updateSysUser(sysUser, roleIds);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("SysUser");
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	/**
	 * 显示用户
	 * 
	 * @throws Exception
	 */
	public String forwordShow() throws Exception {
		return "forwordShow";
	}

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	public String ajaxDelete() throws Exception {
		sysUser = sysUserService.getSysUserBean(userId);
		sysUser.setStatus(2);
		sysUserService.updateSysUser(sysUser);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		ajaxBeanData.setResult("sucess");
		ajaxBeanData.setResume("成功");
		ajaxBeanData.setLabel("sysUser");
		jsonString = JSONObjectTool.getObjectJson(ajaxBeanData);
		return "jsonString";
	}

	/**
	 * 检测当前用户是否存在
	 * 
	 * @throws Exception
	 */
	public String checkUser() throws Exception {
		List tempList = sysUserService.checkUser(loginName);
		AjaxBeanData ajaxBeanData = new AjaxBeanData();
		if (tempList == null || tempList.size() == 0) {
			ajaxBeanData.setResult("sucess");
			ajaxBeanData.setResume("成功");
			ajaxBeanData.setLabel("SysUser");
		} else {
			ajaxBeanData.setResult("faild");
			ajaxBeanData.setResume("当前帐号" + loginName + "已经存在！");
			ajaxBeanData.setLabel("SysUser");
		}
		operateResultString = JSONObjectTool.getJson(ajaxBeanData);
		return "ajaxData";
	}

	/**
	 * 修改当前用户密码
	 * zhousu
	 * @throws Exception
	 * 
	 * @throws Exception
	 */
	public String updateCrrPassWord() throws Exception {

		SysUser sysUser = (SysUser) ActionContext.getContext().getSession()
				.get("sysUser");
		if(this.encryptPassword(oldpassword).equals(sysUser.getPassword())){
			if (password != null && !"".equals(password)) {
				sysUser.setPassword(this.encryptPassword(password));
			}
			sysUserService.updateSysUser(sysUser);
			ActionContext.getContext().getSession().clear();
			msg="密码修改成功，请重新登录！";
			return "success";
		}else{
			msg = "<font color='red'>原密码输入错误，请重新输入！</font>";
			return "forwordUserPassUpad";
		}
		
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}
	public InputStream getOperateResultString() {
		return operateResultString;
	}

	public void setOperateResultString(InputStream operateResultString) {
		this.operateResultString = operateResultString;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public String getLoginNameSelect() {
		return loginNameSelect;
	}

	public void setLoginNameSelect(String loginNameSelect) {
		this.loginNameSelect = loginNameSelect;
	}

	public String getTelephoneSelect() {
		return telephoneSelect;
	}

	public void setTelephoneSelect(String telephoneSelect) {
		this.telephoneSelect = telephoneSelect;
	}

	public String getRoleSelect() {
		return roleSelect;
	}

	public void setRoleSelect(String roleSelect) {
		this.roleSelect = roleSelect;
	}

	public long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysAreaService getSysAreaService() {
		return sysAreaService;
	}

	public void setSysAreaService(SysAreaService sysAreaService) {
		this.sysAreaService = sysAreaService;
	}

	public String getUserNameSelect() {
		return userNameSelect;
	}

	public void setUserNameSelect(String userNameSelect) {
		this.userNameSelect = userNameSelect;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaMenu() {
		return areaMenu;
	}

	public void setAreaMenu(String areaMenu) {
		this.areaMenu = areaMenu;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public List getAreaList() {
		return areaList;
	}
	public void setAreaList(List areaList) {
		this.areaList = areaList;
	}
	public String getRoleString() {
		return roleString;
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	public String getAreaString() {
		return areaString;
	}
	public void setAreaString(String areaString) {
		this.areaString = areaString;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getMenuselected() {
		return menuselected;
	}
	public void setMenuselected(String menuselected) {
		this.menuselected = menuselected;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getOpenOperator() {
		return openOperator;
	}
	public void setOpenOperator(String openOperator) {
		this.openOperator = openOperator;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Date getEffectTime() {
		return effectTime;
	}
	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}
	public Date getLoginBeginTime() {
		return loginBeginTime;
	}
	public void setLoginBeginTime(Date loginBeginTime) {
		this.loginBeginTime = loginBeginTime;
	}
	public Date getLoginEndTime() {
		return loginEndTime;
	}
	public void setLoginEndTime(Date loginEndTime) {
		this.loginEndTime = loginEndTime;
	}
	public Integer getMaxOnline() {
		return maxOnline;
	}
	public void setMaxOnline(Integer maxOnline) {
		this.maxOnline = maxOnline;
	}
	
	public String getAdminType() {
		return adminType;
	}
	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}
	public String getShenDate() {
		return shenDate;
	}
	public void setShenDate(String shenDate) {
		this.shenDate = shenDate;
	}
	public String getShiDate() {
		return shiDate;
	}
	public void setShiDate(String shiDate) {
		this.shiDate = shiDate;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getAreaStrings() {
		return areaStrings;
	}
	public void setAreaStrings(String areaStrings) {
		this.areaStrings = areaStrings;
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
	public static final String encryptPassword(String password){
		char[] ELEMENTS_ENCRYPT = {
	        '0', '3', '6', '9', '2', '5', '8', '1', '4', '7', 'A', 'F', 'K', 'P', 'U',
	        'Z'};
		return MessageDigest.encrypt(password, "md5", ELEMENTS_ENCRYPT);
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	
}
