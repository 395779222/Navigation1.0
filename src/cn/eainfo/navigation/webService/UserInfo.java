package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for userInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="userInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="districtCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="districtName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="setTopBoxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smartCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TVCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userRank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userInfo", propOrder = { "districtCode", "districtName",
		"phoneNumber", "setTopBoxId", "smartCardId", "status", "tvCode",
		"userCode", "userName", "userRank", "userType","cityCode" })
public class UserInfo {
	protected String cityCode;
	protected String districtCode;
	protected String districtName;
	protected String phoneNumber;
	protected String setTopBoxId;
	protected String smartCardId;
	protected String status;
	@XmlElement(name = "TVCode")
	protected String tvCode;
	protected String userCode;
	protected String userName;
	protected String userRank;
	protected String userType;

	/**
	 * Gets the value of the districtCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDistrictCode() {
		return districtCode;
	}

	/**
	 * Sets the value of the districtCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDistrictCode(String value) {
		this.districtCode = value;
	}

	/**
	 * Gets the value of the districtName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * Sets the value of the districtName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDistrictName(String value) {
		this.districtName = value;
	}

	/**
	 * Gets the value of the phoneNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the value of the phoneNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPhoneNumber(String value) {
		this.phoneNumber = value;
	}

	/**
	 * Gets the value of the setTopBoxId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSetTopBoxId() {
		return setTopBoxId;
	}

	/**
	 * Sets the value of the setTopBoxId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSetTopBoxId(String value) {
		this.setTopBoxId = value;
	}

	/**
	 * Gets the value of the smartCardId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSmartCardId() {
		return smartCardId;
	}

	/**
	 * Sets the value of the smartCardId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSmartCardId(String value) {
		this.smartCardId = value;
	}

	/**
	 * Gets the value of the status property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the value of the status property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus(String value) {
		this.status = value;
	}

	/**
	 * Gets the value of the tvCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTVCode() {
		return tvCode;
	}

	/**
	 * Sets the value of the tvCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTVCode(String value) {
		this.tvCode = value;
	}

	/**
	 * Gets the value of the userCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * Sets the value of the userCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserCode(String value) {
		this.userCode = value;
	}

	/**
	 * Gets the value of the userName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserName(String value) {
		this.userName = value;
	}

	/**
	 * Gets the value of the userRank property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserRank() {
		return userRank;
	}

	/**
	 * Sets the value of the userRank property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserRank(String value) {
		this.userRank = value;
	}

	/**
	 * Gets the value of the userType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * Sets the value of the userType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserType(String value) {
		this.userType = value;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
