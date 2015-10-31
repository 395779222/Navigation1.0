package cn.eainfo.system.model;

import cn.eainfo.common.BaseModel;

/**
 * 用户查询模型
 */
public class SysUserModel extends BaseModel {
	private String userName;// 用户名称
	private String loginName;// 登录帐号
	private String password;// 登录密码 md5加密
	private String telephone;// 联系电话
	private int status;// 用户当前状态 1.正常 2.注销
	private String areaNo;
	private String areaCode;
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
