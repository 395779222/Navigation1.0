package cn.eainfo.navigation.webService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "CoshipService4Business", targetNamespace = "http://service.business.coship.com/")
public interface CoshipService4Business {

	/**
	 * 
	 * @param queryInfo
	 * @return returns cn.eainfo.navigation.webService.ResultInfo
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "queryUserInfo", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.QueryUserInfo")
	@ResponseWrapper(localName = "queryUserInfoResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.QueryUserInfoResponse")
	public ResultInfo queryUserInfo(
			@WebParam(name = "queryInfo", targetNamespace = "") QueryInfo queryInfo);

	/**
	 * 
	 * @param amspQueryGroup
	 * @return returns cn.eainfo.navigation.webService.AmspQueryGroupRESP
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "queryGroupInfo", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.QueryGroupInfo")
	@ResponseWrapper(localName = "queryGroupInfoResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.QueryGroupInfoResponse")
	public AmspQueryGroupRESP queryGroupInfo(
			@WebParam(name = "AMSP_Query_Group", targetNamespace = "") AmspQueryGroup amspQueryGroup);

	/**
	 * 
	 * @param amspAuthProd
	 * @return returns cn.eainfo.navigation.webService.AmspAuthProdRESP
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "authProduct", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthProduct")
	@ResponseWrapper(localName = "authProductResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthProductResponse")
	public AmspAuthProdRESP authProduct(
			@WebParam(name = "AMSP_Auth_Prod", targetNamespace = "") AmspAuthProd amspAuthProd);

	/**
	 * 
	 * @param amspAuthUser
	 * @return returns cn.eainfo.navigation.webService.AmspAuthUserRESP
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "authUser", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthUser")
	@ResponseWrapper(localName = "authUserResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthUserResponse")
	public AmspAuthUserRESP authUser(
			@WebParam(name = "AMSP_Auth_User", targetNamespace = "") AmspAuthUser amspAuthUser);

	/**
	 * 
	 * @param amspRegIMSUser
	 * @return returns cn.eainfo.navigation.webService.AmspRegIMSUserRESP
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "registerIMS", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.RegisterIMS")
	@ResponseWrapper(localName = "registerIMSResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.RegisterIMSResponse")
	public AmspRegIMSUserRESP registerIMS(
			@WebParam(name = "AMSP_Reg_IMSUser", targetNamespace = "") AmspRegIMSUser amspRegIMSUser);

	/**
	 * 
	 * @param amspAuthIMSUser
	 * @return returns cn.eainfo.navigation.webService.AmspAuthIMSUserRESP
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "authIMS", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthIMS")
	@ResponseWrapper(localName = "authIMSResponse", targetNamespace = "http://service.business.coship.com/", className = "cn.eainfo.navigation.webService.AuthIMSResponse")
	public AmspAuthIMSUserRESP authIMS(
			@WebParam(name = "AMSP_Auth_IMSUser", targetNamespace = "") AmspAuthIMSUser amspAuthIMSUser);

}