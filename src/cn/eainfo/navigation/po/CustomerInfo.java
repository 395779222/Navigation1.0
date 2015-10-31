/*
 * 文 件 名:  CustomerInfo.java
 * 版    权:  Copyright (c) qixin Coperation.
 * 描    述:  <描述>
 * 修 改 人:  Administrator
 * 修改时间:  2014-4-12
 * 修改内容:  <修改内容>
 */
package cn.eainfo.navigation.po;

/**
 * <功能详细描述>
 * @author  Administrator
 * @version  [版本号, 2014-4-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CustomerInfo {
	private Integer id;
	private String districtCode;
	private String districtName;
	private String areaNo;
	private String cardNo;
	/**
	 * @return 返回 id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param 对id进行赋值
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return 返回 districtCode
	 */
	public String getDistrictCode() {
		return districtCode;
	}
	/**
	 * @param 对districtCode进行赋值
	 */
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	/**
	 * @return 返回 districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param 对districtName进行赋值
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	 * @return 返回 cradNo
	 */
	public String getCardNo() {
		return cardNo;
	}
	/**
	 * @param 对cradNo进行赋值
	 */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
}
