package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for registerIMSResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="registerIMSResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.business.coship.com/}amspRegIMSUserRESP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerIMSResponse", propOrder = { "_return" })
public class RegisterIMSResponse {

	@XmlElement(name = "return")
	protected AmspRegIMSUserRESP _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link AmspRegIMSUserRESP }
	 * 
	 */
	public AmspRegIMSUserRESP getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspRegIMSUserRESP }
	 * 
	 */
	public void setReturn(AmspRegIMSUserRESP value) {
		this._return = value;
	}

}
