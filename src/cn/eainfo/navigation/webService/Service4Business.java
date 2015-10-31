package cn.eainfo.navigation.webService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * Service4business service = new Service4business();
 * CoshipService4Business portType = service.getCoshipService4BusinessPort();
 * portType.queryUserInfo(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "Service4business", targetNamespace = "http://service.business.coship.com/", wsdlLocation = "http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl")
public class Service4Business extends Service {

	private final static URL SERVICE4BUSINESS_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(cn.eainfo.navigation.webService.Service4Business.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = cn.eainfo.navigation.webService.Service4Business.class
					.getResource(".");
			url = new URL(baseUrl,
					"http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://172.31.178.4:18080/aaa/Business/CoshipService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		SERVICE4BUSINESS_WSDL_LOCATION = url;
	}

	public Service4Business(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public Service4Business() {
		super(SERVICE4BUSINESS_WSDL_LOCATION, new QName(
				"http://service.business.coship.com/", "Service4business"));
	}

	/**
	 * 
	 * @return returns CoshipService4Business
	 */
	@WebEndpoint(name = "CoshipService4BusinessPort")
	public CoshipService4Business getCoshipService4BusinessPort() {
		return super.getPort(new QName("http://service.business.coship.com/",
				"CoshipService4BusinessPort"), CoshipService4Business.class);
	}

}
