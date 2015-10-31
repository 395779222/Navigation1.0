package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authIMS complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authIMS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AMSP_Auth_IMSUser" type="{http://service.business.coship.com/}amspAuthIMSUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authIMS", propOrder = { "amspAuthIMSUser" })
public class AuthIMS {

	@XmlElement(name = "AMSP_Auth_IMSUser")
	protected AmspAuthIMSUser amspAuthIMSUser;

	/**
	 * Gets the value of the amspAuthIMSUser property.
	 * 
	 * @return possible object is {@link AmspAuthIMSUser }
	 * 
	 */
	public AmspAuthIMSUser getAMSPAuthIMSUser() {
		return amspAuthIMSUser;
	}

	/**
	 * Sets the value of the amspAuthIMSUser property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspAuthIMSUser }
	 * 
	 */
	public void setAMSPAuthIMSUser(AmspAuthIMSUser value) {
		this.amspAuthIMSUser = value;
	}

}
