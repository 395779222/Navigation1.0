package cn.eainfo.navigation.webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for queryUserInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="queryUserInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="queryInfo" type="{http://service.business.coship.com/}queryInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryUserInfo", propOrder = { "queryInfo" })
public class QueryUserInfo {

	protected QueryInfo queryInfo;

	/**
	 * Gets the value of the queryInfo property.
	 * 
	 * @return possible object is {@link QueryInfo }
	 * 
	 */
	public QueryInfo getQueryInfo() {
		return queryInfo;
	}

	/**
	 * Sets the value of the queryInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link QueryInfo }
	 * 
	 */
	public void setQueryInfo(QueryInfo value) {
		this.queryInfo = value;
	}

}
