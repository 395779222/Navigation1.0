package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for amspAuthProd complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="amspAuthProd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="authType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resourceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tvCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amspAuthProd", propOrder = { "authType", "productCode",
		"resourceCode", "spCode", "tvCode", "userCode" })
public class AmspAuthProd {

	protected Integer authType;
	protected String productCode;
	protected String resourceCode;
	protected String spCode;
	protected String tvCode;
	protected String userCode;

	/**
	 * Gets the value of the authType property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getAuthType() {
		return authType;
	}

	/**
	 * Sets the value of the authType property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setAuthType(Integer value) {
		this.authType = value;
	}

	/**
	 * Gets the value of the productCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Sets the value of the productCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProductCode(String value) {
		this.productCode = value;
	}

	/**
	 * Gets the value of the resourceCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResourceCode() {
		return resourceCode;
	}

	/**
	 * Sets the value of the resourceCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResourceCode(String value) {
		this.resourceCode = value;
	}

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

}
