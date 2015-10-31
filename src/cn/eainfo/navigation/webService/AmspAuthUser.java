package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for amspAuthUser complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="amspAuthUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="spCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tvCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amspAuthUser", propOrder = { "spCode", "tvCode", "userCode",
		"userToken" })
public class AmspAuthUser {

	protected String spCode;
	protected String tvCode;
	protected String userCode;
	protected String userToken;

	/**
	 * Gets the value of the spCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSpCode() {
		return spCode;
	}

	/**
	 * Sets the value of the spCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSpCode(String value) {
		this.spCode = value;
	}

	/**
	 * Gets the value of the tvCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTvCode() {
		return tvCode;
	}

	/**
	 * Sets the value of the tvCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTvCode(String value) {
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
	 * Gets the value of the userToken property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserToken() {
		return userToken;
	}

	/**
	 * Sets the value of the userToken property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserToken(String value) {
		this.userToken = value;
	}

}
