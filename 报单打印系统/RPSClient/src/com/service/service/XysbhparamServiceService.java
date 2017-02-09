
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
* XysbhparamServiceService service = new XysbhparamServiceService();
* XysbhparamServiceDelegate portType = service.getXysbhparamServicePort();
* portType.savexysbh(...);
 * </pre>
 * </p>
 * 
 */
@WebServiceClient(name = "XysbhparamServiceService", targetNamespace = "http://service.service.com/", wsdlLocation = "http://47.91.140.252:7001/RPSService/XysbhparamServicePort?wsdl")
public class XysbhparamServiceService extends Service {

	private final static URL XYSBHPARAMSERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger.getLogger(com.service.service.XysbhparamServiceService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.service.service.XysbhparamServiceService.class.getResource(".");
			url = new URL(baseUrl, "http://47.91.140.252:7001/RPSService/XysbhparamServicePort?wsdl");
		} catch (MalformedURLException e) {
			logger.warning(
					"Failed to create URL for the wsdl Location: 'http://47.91.140.252:7001/RPSService/XysbhparamServicePort?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		XYSBHPARAMSERVICESERVICE_WSDL_LOCATION = url;
	}

	public XysbhparamServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public XysbhparamServiceService() {
		super(XYSBHPARAMSERVICESERVICE_WSDL_LOCATION,
				new QName("http://service.service.com/", "XysbhparamServiceService"));
	}

	/**
	 * 
	 * @return returns XysbhparamServiceDelegate
	 */
	@WebEndpoint(name = "XysbhparamServicePort")
	public XysbhparamServiceDelegate getXysbhparamServicePort() {
		return super.getPort(new QName("http://service.service.com/", "XysbhparamServicePort"),
				XysbhparamServiceDelegate.class);
	}

}
