package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authUser complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AMSP_Auth_User" type="{http://service.business.coship.com/}amspAuthUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authUser", propOrder = { "amspAuthUser" })
public class AuthUser {

	@XmlElement(name = "AMSP_Auth_User")
	protected AmspAuthUser amspAuthUser;

	/**
	 * Gets the value of the amspAuthUser property.
	 * 
	 * @return possible object is {@link AmspAuthUser }
	 * 
	 */
	public AmspAuthUser getAMSPAuthUser() {
		return amspAuthUser;
	}

	/**
	 * Sets the value of the amspAuthUser property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspAuthUser }
	 * 
	 */
	public void setAMSPAuthUser(AmspAuthUser value) {
		this.amspAuthUser = value;
	}

}
