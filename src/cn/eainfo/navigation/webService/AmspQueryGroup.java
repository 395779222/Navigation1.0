package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for amspQueryGroup complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="amspQueryGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryParameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="queryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="setTopBoxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smartCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TVCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amspQueryGroup", propOrder = { "queryParameter", "queryType",
		"setTopBoxId", "smartCardId", "tvCode", "userId" })
public class AmspQueryGroup {

	protected String queryParameter;
	protected String queryType;
	protected String setTopBoxId;
	protected String smartCardId;
	@XmlElement(name = "TVCode")
	protected String tvCode;
	protected String userId;

	/**
	 * Gets the value of the queryParameter property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQueryParameter() {
		return queryParameter;
	}

	/**
	 * Sets the value of the queryParameter property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQueryParameter(String value) {
		this.queryParameter = value;
	}

	/**
	 * Gets the value of the queryType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getQueryType() {
		return queryType;
	}

	/**
	 * Sets the value of the queryType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setQueryType(String value) {
		this.queryType = value;
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
	 * Gets the value of the userId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the userId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserId(String value) {
		this.userId = value;
	}

}
