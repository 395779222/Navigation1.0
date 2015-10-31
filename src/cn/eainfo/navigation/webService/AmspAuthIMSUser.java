package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for amspAuthIMSUser complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="amspAuthIMSUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="setTopBoxId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smartCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "amspAuthIMSUser", propOrder = { "setTopBoxId", "smartCardId" })
public class AmspAuthIMSUser {

	protected String setTopBoxId;
	protected String smartCardId;

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

}
