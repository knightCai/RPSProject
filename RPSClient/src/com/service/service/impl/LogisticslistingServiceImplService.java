
package com.service.service.impl;

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
* LogisticslistingServiceImplService service = new LogisticslistingServiceImplService();
* LogisticslistingServiceImpl portType = service.getLogisticslistingServiceImplPort();
* portType.findLlistByParams(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "LogisticslistingServiceImplService", targetNamespace = "http://impl.service.service.com/", wsdlLocation = "http://47.91.140.252:7001/RPSService/LogisticslistingServiceImplPort?wsdl")
public class LogisticslistingServiceImplService extends Service {

	private final static URL LOGISTICSLISTINGSERVICEIMPLSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.service.service.impl.LogisticslistingServiceImplService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.service.service.impl.LogisticslistingServiceImplService.class.getResource(".");
			url = new URL(baseUrl, "http://47.91.140.252:7001/RPSService/LogisticslistingServiceImplPort?wsdl");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://47.91.140.252:7001/RPSService/LogisticslistingServiceImplPort?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		LOGISTICSLISTINGSERVICEIMPLSERVICE_WSDL_LOCATION = url;
	}

	public LogisticslistingServiceImplService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public LogisticslistingServiceImplService() {
		super(LOGISTICSLISTINGSERVICEIMPLSERVICE_WSDL_LOCATION,
				new QName("http://impl.service.service.com/", "LogisticslistingServiceImplService"));
	}

	/**
	 * 
	 * @return returns LogisticslistingServiceImpl
	 */
	@WebEndpoint(name = "LogisticslistingServiceImplPort")
	public LogisticslistingServiceImpl getLogisticslistingServiceImplPort() {
		return super.getPort(new QName("http://impl.service.service.com/", "LogisticslistingServiceImplPort"),
				LogisticslistingServiceImpl.class);
	}

}