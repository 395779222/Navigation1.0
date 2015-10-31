/*
 * 文 件 名:  Release.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-5
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.po;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-5]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Release {
	private long id;
	private String name;
	private String ip;
	private String port;
	private String userName;
	private String pass;
	private String address;
	private String type;
	private String url;
	private String areaNo;
	private String deleteFlag;
	/**
	 * @return 返回 areaNo
	 */
	public String getAreaNo() {
		return areaNo;
	}
	/**
	 * @param 对areaNo进行赋值
	 */
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	/**
	 * @return 返回 id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param 对id进行赋值
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return 返回 name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 对name进行赋值
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return 返回 ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param 对ip进行赋值
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return 返回 port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param 对port进行赋值
	 */
	public void setPort(String port) {
		this.port = port;
	}
	/**
	 * @return 返回 username
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @return 返回 pass
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * @param 对pass进行赋值
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * @return 返回 address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param 对address进行赋值
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return 返回 type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param 对type进行赋值
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return 返回 url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param 对url进行赋值
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return 返回 deleteFlag
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}
	/**
	 * @param 对deleteFlag进行赋值
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * @param 对userName进行赋值
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
