package cn.eainfo.navigation.webService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the cn.eainfo.navigation.webService package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _AuthProduct_QNAME = new QName(
			"http://service.business.coship.com/", "authProduct");
	private final static QName _AuthUserResponse_QNAME = new QName(
			"http://service.business.coship.com/", "authUserResponse");
	private final static QName _RegisterIMS_QNAME = new QName(
			"http://service.business.coship.com/", "registerIMS");
	private final static QName _QueryGroupInfoResponse_QNAME = new QName(
			"http://service.business.coship.com/", "queryGroupInfoResponse");
	private final static QName _AuthUser_QNAME = new QName(
			"http://service.business.coship.com/", "authUser");
	private final static QName _QueryUserInfo_QNAME = new QName(
			"http://service.business.coship.com/", "queryUserInfo");
	private final static QName _AuthIMS_QNAME = new QName(
			"http://service.business.coship.com/", "authIMS");
	private final static QName _QueryUserInfoResponse_QNAME = new QName(
			"http://service.business.coship.com/", "queryUserInfoResponse");
	private final static QName _QueryGroupInfo_QNAME = new QName(
			"http://service.business.coship.com/", "queryGroupInfo");
	private final static QName _AuthIMSResponse_QNAME = new QName(
			"http://service.business.coship.com/", "authIMSResponse");
	private final static QName _RegisterIMSResponse_QNAME = new QName(
			"http://service.business.coship.com/", "registerIMSResponse");
	private final static QName _AuthProductResponse_QNAME = new QName(
			"http://service.business.coship.com/", "authProductResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: cn.eainfo.navigation.webService
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link AuthUserResponse }
	 * 
	 */
	public AuthUserResponse createAuthUserResponse() {
		return new AuthUserResponse();
	}

	/**
	 * Create an instance of {@link QueryInfo }
	 * 
	 */
	public QueryInfo createQueryInfo() {
		return new QueryInfo();
	}

	/**
	 * Create an instance of {@link QueryUserInfoResponse }
	 * 
	 */
	public QueryUserInfoResponse createQueryUserInfoResponse() {
		return new QueryUserInfoResponse();
	}

	/**
	 * Create an instance of {@link AuthUser }
	 * 
	 */
	public AuthUser createAuthUser() {
		return new AuthUser();
	}

	/**
	 * Create an instance of {@link UserInfo }
	 * 
	 */
	public UserInfo createUserInfo() {
		return new UserInfo();
	}

	/**
	 * Create an instance of {@link AmspAuthUser }
	 * 
	 */
	public AmspAuthUser createAmspAuthUser() {
		return new AmspAuthUser();
	}

	/**
	 * Create an instance of {@link ResultInfo }
	 * 
	 */
	public ResultInfo createResultInfo() {
		return new ResultInfo();
	}

	/**
	 * Create an instance of {@link AmspAuthIMSUser }
	 * 
	 */
	public AmspAuthIMSUser createAmspAuthIMSUser() {
		return new AmspAuthIMSUser();
	}

	/**
	 * Create an instance of {@link RegisterIMS }
	 * 
	 */
	public RegisterIMS createRegisterIMS() {
		return new RegisterIMS();
	}

	/**
	 * Create an instance of {@link AmspQueryGroupRESP }
	 * 
	 */
	public AmspQueryGroupRESP createAmspQueryGroupRESP() {
		return new AmspQueryGroupRESP();
	}

	/**
	 * Create an instance of {@link QueryGroupInfoResponse }
	 * 
	 */
	public QueryGroupInfoResponse createQueryGroupInfoResponse() {
		return new QueryGroupInfoResponse();
	}

	/**
	 * Create an instance of {@link RegisterIMSResponse }
	 * 
	 */
	public RegisterIMSResponse createRegisterIMSResponse() {
		return new RegisterIMSResponse();
	}

	/**
	 * Create an instance of {@link AmspAuthProd }
	 * 
	 */
	public AmspAuthProd createAmspAuthProd() {
		return new AmspAuthProd();
	}

	/**
	 * Create an instance of {@link AmspAuthIMSUserRESP }
	 * 
	 */
	public AmspAuthIMSUserRESP createAmspAuthIMSUserRESP() {
		return new AmspAuthIMSUserRESP();
	}

	/**
	 * Create an instance of {@link AuthIMSResponse }
	 * 
	 */
	public AuthIMSResponse createAuthIMSResponse() {
		return new AuthIMSResponse();
	}

	/**
	 * Create an instance of {@link AmspAuthUserRESP }
	 * 
	 */
	public AmspAuthUserRESP createAmspAuthUserRESP() {
		return new AmspAuthUserRESP();
	}

	/**
	 * Create an instance of {@link AmspAuthProdRESP }
	 * 
	 */
	public AmspAuthProdRESP createAmspAuthProdRESP() {
		return new AmspAuthProdRESP();
	}

	/**
	 * Create an instance of {@link AmspRegIMSUser }
	 * 
	 */
	public AmspRegIMSUser createAmspRegIMSUser() {
		return new AmspRegIMSUser();
	}

	/**
	 * Create an instance of {@link AuthIMS }
	 * 
	 */
	public AuthIMS createAuthIMS() {
		return new AuthIMS();
	}

	/**
	 * Create an instance of {@link QueryGroupInfo }
	 * 
	 */
	public QueryGroupInfo createQueryGroupInfo() {
		return new QueryGroupInfo();
	}

	/**
	 * Create an instance of {@link AmspQueryGroup }
	 * 
	 */
	public AmspQueryGroup createAmspQueryGroup() {
		return new AmspQueryGroup();
	}

	/**
	 * Create an instance of {@link AuthProduct }
	 * 
	 */
	public AuthProduct createAuthProduct() {
		return new AuthProduct();
	}

	/**
	 * Create an instance of {@link AuthProductResponse }
	 * 
	 */
	public AuthProductResponse createAuthProductResponse() {
		return new AuthProductResponse();
	}

	/**
	 * Create an instance of {@link AmspRegIMSUserRESP }
	 * 
	 */
	public AmspRegIMSUserRESP createAmspRegIMSUserRESP() {
		return new AmspRegIMSUserRESP();
	}

	/**
	 * Create an instance of {@link QueryUserInfo }
	 * 
	 */
	public QueryUserInfo createQueryUserInfo() {
		return new QueryUserInfo();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AuthProduct }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authProduct")
	public JAXBElement<AuthProduct> createAuthProduct(AuthProduct value) {
		return new JAXBElement<AuthProduct>(_AuthProduct_QNAME,
				AuthProduct.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AuthUserResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authUserResponse")
	public JAXBElement<AuthUserResponse> createAuthUserResponse(
			AuthUserResponse value) {
		return new JAXBElement<AuthUserResponse>(_AuthUserResponse_QNAME,
				AuthUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RegisterIMS }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "registerIMS")
	public JAXBElement<RegisterIMS> createRegisterIMS(RegisterIMS value) {
		return new JAXBElement<RegisterIMS>(_RegisterIMS_QNAME,
				RegisterIMS.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryGroupInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "queryGroupInfoResponse")
	public JAXBElement<QueryGroupInfoResponse> createQueryGroupInfoResponse(
			QueryGroupInfoResponse value) {
		return new JAXBElement<QueryGroupInfoResponse>(
				_QueryGroupInfoResponse_QNAME, QueryGroupInfoResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AuthUser }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authUser")
	public JAXBElement<AuthUser> createAuthUser(AuthUser value) {
		return new JAXBElement<AuthUser>(_AuthUser_QNAME, AuthUser.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link QueryUserInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "queryUserInfo")
	public JAXBElement<QueryUserInfo> createQueryUserInfo(QueryUserInfo value) {
		return new JAXBElement<QueryUserInfo>(_QueryUserInfo_QNAME,
				QueryUserInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AuthIMS }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authIMS")
	public JAXBElement<AuthIMS> createAuthIMS(AuthIMS value) {
		return new JAXBElement<AuthIMS>(_AuthIMS_QNAME, AuthIMS.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryUserInfoResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "queryUserInfoResponse")
	public JAXBElement<QueryUserInfoResponse> createQueryUserInfoResponse(
			QueryUserInfoResponse value) {
		return new JAXBElement<QueryUserInfoResponse>(
				_QueryUserInfoResponse_QNAME, QueryUserInfoResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link QueryGroupInfo }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "queryGroupInfo")
	public JAXBElement<QueryGroupInfo> createQueryGroupInfo(QueryGroupInfo value) {
		return new JAXBElement<QueryGroupInfo>(_QueryGroupInfo_QNAME,
				QueryGroupInfo.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AuthIMSResponse }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authIMSResponse")
	public JAXBElement<AuthIMSResponse> createAuthIMSResponse(
			AuthIMSResponse value) {
		return new JAXBElement<AuthIMSResponse>(_AuthIMSResponse_QNAME,
				AuthIMSResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link RegisterIMSResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "registerIMSResponse")
	public JAXBElement<RegisterIMSResponse> createRegisterIMSResponse(
			RegisterIMSResponse value) {
		return new JAXBElement<RegisterIMSResponse>(_RegisterIMSResponse_QNAME,
				RegisterIMSResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AuthProductResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.business.coship.com/", name = "authProductResponse")
	public JAXBElement<AuthProductResponse> createAuthProductResponse(
			AuthProductResponse value) {
		return new JAXBElement<AuthProductResponse>(_AuthProductResponse_QNAME,
				AuthProductResponse.class, null, value);
	}

}
