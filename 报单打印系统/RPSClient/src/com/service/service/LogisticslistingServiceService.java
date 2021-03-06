
package com.service.service;

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
* LogisticslistingServiceService service = new LogisticslistingServiceService();
* LogisticslistingServiceDelegate portType = service.getLogisticslistingServicePort();
* portType.saveLlist(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "LogisticslistingServiceService", targetNamespace = "http://service.service.com/", wsdlLocation = "http://47.91.140.252:7001/RPSService/LogisticslistingServicePort?wsdl")
public class LogisticslistingServiceService extends Service {

	private final static URL LOGISTICSLISTINGSERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.service.service.LogisticslistingServiceService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.service.service.LogisticslistingServiceService.class.getResource(".");
			url = new URL(baseUrl, "http://47.91.140.252:7001/RPSService/LogisticslistingServicePort?wsdl");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://47.91.140.252:7001/RPSService/LogisticslistingServicePort?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		LOGISTICSLISTINGSERVICESERVICE_WSDL_LOCATION = url;
	}

	public LogisticslistingServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public LogisticslistingServiceService() {
		super(LOGISTICSLISTINGSERVICESERVICE_WSDL_LOCATION,
				new QName("http://service.service.com/", "LogisticslistingServiceService"));
	}

	/**
	 * 
	 * @return returns LogisticslistingServiceDelegate
	 */
	@WebEndpoint(name = "LogisticslistingServicePort")
	public LogisticslistingServiceDelegate getLogisticslistingServicePort() {
		return super.getPort(new QName("http://service.service.com/", "LogisticslistingServicePort"),
				LogisticslistingServiceDelegate.class);
	}

}
