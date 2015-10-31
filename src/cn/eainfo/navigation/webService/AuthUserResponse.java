package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authUserResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.business.coship.com/}amspAuthUserRESP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authUserResponse", propOrder = { "_return" })
public class AuthUserResponse {

	@XmlElement(name = "return")
	protected AmspAuthUserRESP _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link AmspAuthUserRESP }
	 * 
	 */
	public AmspAuthUserRESP getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspAuthUserRESP }
	 * 
	 */
	public void setReturn(AmspAuthUserRESP value) {
		this._return = value;
	}

}
