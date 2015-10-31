package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for authProduct complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="authProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AMSP_Auth_Prod" type="{http://service.business.coship.com/}amspAuthProd" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "authProduct", propOrder = { "amspAuthProd" })
public class AuthProduct {

	@XmlElement(name = "AMSP_Auth_Prod")
	protected AmspAuthProd amspAuthProd;

	/**
	 * Gets the value of the amspAuthProd property.
	 * 
	 * @return possible object is {@link AmspAuthProd }
	 * 
	 */
	public AmspAuthProd getAMSPAuthProd() {
		return amspAuthProd;
	}

	/**
	 * Sets the value of the amspAuthProd property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspAuthProd }
	 * 
	 */
	public void setAMSPAuthProd(AmspAuthProd value) {
		this.amspAuthProd = value;
	}

}
