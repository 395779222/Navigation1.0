/*
 * 文 件 名:  Shortcuts.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-6
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.po;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-6]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Shortcuts {
	private long id;
	private String url;
	private String namena;
	private String icon;
	private Integer key;
	private String type;
	private String areaNo;
	private String checkState;
	private String cityEnName;
	
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
	 * @return 返回 icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param 对icon进行赋值
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return 返回 key
	 */
	public Integer getKey() {
		return key;
	}
	/**
	 * @param 对key进行赋值
	 */
	public void setKey(Integer key) {
		this.key = key;
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
	 * @return 返回 checkState
	 */
	public String getCheckState() {
		return checkState;
	}
	/**
	 * @param 对checkState进行赋值
	 */
	public void setCheckState(String checkState) {
		this.checkState = checkState;
	}
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
	
}
