package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for amspQueryGroupRESP complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="amspQueryGroupRESP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amspQueryGroupRESP", propOrder = { "resultCode", "userGroup",
		"userGroupName" })
public class AmspQueryGroupRESP {

	protected String resultCode;
	protected String userGroup;
	protected String userGroupName;

	/**
	 * Gets the value of the resultCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResultCode() {
		return resultCode;
	}

	/**
	 * Sets the value of the resultCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResultCode(String value) {
		this.resultCode = value;
	}

	/**
	 * Gets the value of the userGroup property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserGroup() {
		return userGroup;
	}

	/**
	 * Sets the value of the userGroup property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserGroup(String value) {
		this.userGroup = value;
	}

	/**
	 * Gets the value of the userGroupName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserGroupName() {
		return userGroupName;
	}

	/**
	 * Sets the value of the userGroupName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserGroupName(String value) {
		this.userGroupName = value;
	}

}
