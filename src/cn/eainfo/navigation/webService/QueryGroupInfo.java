package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryGroupInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="queryGroupInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AMSP_Query_Group" type="{http://service.business.coship.com/}amspQueryGroup" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryGroupInfo", propOrder = { "amspQueryGroup" })
public class QueryGroupInfo {

	@XmlElement(name = "AMSP_Query_Group")
	protected AmspQueryGroup amspQueryGroup;

	/**
	 * Gets the value of the amspQueryGroup property.
	 * 
	 * @return possible object is {@link AmspQueryGroup }
	 * 
	 */
	public AmspQueryGroup getAMSPQueryGroup() {
		return amspQueryGroup;
	}

	/**
	 * Sets the value of the amspQueryGroup property.
	 * 
	 * @param value
	 *            allowed object is {@link AmspQueryGroup }
	 * 
	 */
	public void setAMSPQueryGroup(AmspQueryGroup value) {
		this.amspQueryGroup = value;
	}

}
