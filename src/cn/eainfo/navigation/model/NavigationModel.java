/*
 * 文 件 名:  NavigationModel.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-3-31
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.model;

import cn.eainfo.common.BaseModel;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-3-31]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class NavigationModel  extends BaseModel {
	private String areaNo;
	private Integer num;
	private String namena;
	private String type;
	private String state;
	/**
	 * @return 返回 stste
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param 对stste进行赋值
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return 返回 num
	 */
	public Integer getNum() {
		return num;
	}
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
	 * @return 返回 location
	 */
	public Integer getLocation() {
		return num;
	}
	/**
	 * @param 对location进行赋值
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * @return 返回 namena
	 */
	public String getNamena() {
		return namena;
	}
	/**
	 * @param 对namena进行赋值
	 */
	public void setNamena(String namena) {
		this.namena = namena;
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
}
