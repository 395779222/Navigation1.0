package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authProductResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authProductResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.business.coship.com/}amspAuthProdRESP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authProductResponse", propOrder = { "_return" })
public class AuthProductResponse {

	@XmlElement(name = "return")
	protected AmspAuthProdRESP _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link AmspAuthProdRESP }
	 * 
	 */
	public AmspAuthProdRESP getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspAuthProdRESP }
	 * 
	 */
	public void setReturn(AmspAuthProdRESP value) {
		this._return = value;
	}

}
