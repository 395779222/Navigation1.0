package cn.eainfo.system.po;

import java.util.Date;

/**
 * 系统用户管理
 * 
 */

public class SysUser {
	// 数据库字段
	private long userId;// 用户编号
	private String userName;// 用户名称
	private String loginName;// 登录帐号
	private String password;// 登录密码 md5加密
	private String areaNo;// 地区编号 当前默认为江苏 32
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
	private String ip;//ip为1需要二次鉴权，2不需要
	private Date createTime;//创建时间
	private String openOperator;
	private Date effectTime;//生效日期
	private Date expireTime;	
	private Date loginBeginTime;
	private Date loginEndTime;
	private Integer maxOnline;
	private String adminType;
	private Integer areaLevel;
	
	private String shenDate;
	private String shiDate;
	private String logDate;
	private String endDate;
	private String userRoleName;
	private String cityEnName;
	
	private String roleString;//获取 角色
	
	
	/**
	 * @return 返回 cityEnName
	 */
	public String getCityEnName() {
		return cityEnName;
	}

	/**
	 * @param 对cityEnName进行赋值
	 */
	public void setCityEnName(String cityEnName) {
		this.cityEnName = cityEnName;
	}

	public Integer getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
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

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
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

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
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

	/**
	 * @return 返回 userRoleName
	 */
	public String getUserRoleName() {
		return userRoleName;
	}

	/**
	 * @param 对userRoleName进行赋值
	 */
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	
}
