package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryGroupInfoResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="queryGroupInfoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.business.coship.com/}amspQueryGroupRESP" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryGroupInfoResponse", propOrder = { "_return" })
public class QueryGroupInfoResponse {

	@XmlElement(name = "return")
	protected AmspQueryGroupRESP _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link AmspQueryGroupRESP }
	 * 
	 */
	public AmspQueryGroupRESP getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspQueryGroupRESP }
	 * 
	 */
	public void setReturn(AmspQueryGroupRESP value) {
		this._return = value;
	}

}
