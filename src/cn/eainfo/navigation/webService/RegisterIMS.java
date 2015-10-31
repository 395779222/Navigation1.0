package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for registerIMS complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="registerIMS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AMSP_Reg_IMSUser" type="{http://service.business.coship.com/}amspRegIMSUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerIMS", propOrder = { "amspRegIMSUser" })
public class RegisterIMS {

	@XmlElement(name = "AMSP_Reg_IMSUser")
	protected AmspRegIMSUser amspRegIMSUser;

	/**
	 * Gets the value of the amspRegIMSUser property.
	 * 
	 * @return possible object is {@link AmspRegIMSUser }
	 * 
	 */
	public AmspRegIMSUser getAMSPRegIMSUser() {
		return amspRegIMSUser;
	}

	/**
	 * Sets the value of the amspRegIMSUser property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspRegIMSUser }
	 * 
	 */
	public void setAMSPRegIMSUser(AmspRegIMSUser value) {
		this.amspRegIMSUser = value;
	}

}
