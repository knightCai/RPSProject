
package com.service.service.impl;

import java.util.List;
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
@WebService(name = "OddnumberServiceImpl", targetNamespace = "http://impl.service.service.com/")
public interface OddnumberServiceImpl {

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "deleteOddnum", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.DeleteOddnum")
	@ResponseWrapper(localName = "deleteOddnumResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.DeleteOddnumResponse")
	public String deleteOddnum(@WebParam(name = "arg0", targetNamespace = "") Oddnumber arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns com.service.service.impl.Oddnumber
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findOddnumByPkId", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.FindOddnumByPkId")
	@ResponseWrapper(localName = "findOddnumByPkIdResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.FindOddnumByPkIdResponse")
	public Oddnumber findOddnumByPkId(@WebParam(name = "arg0", targetNamespace = "") String arg0);

	/**
	 * 
	 * @return returns java.util.List<com.service.service.impl.Oddnumber>
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "findAllOddnum", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.FindAllOddnum")
	@ResponseWrapper(localName = "findAllOddnumResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.FindAllOddnumResponse")
	public List<Oddnumber> findAllOddnum();

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveOddnum", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.SaveOddnum")
	@ResponseWrapper(localName = "saveOddnumResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.SaveOddnumResponse")
	public String saveOddnum(@WebParam(name = "arg0", targetNamespace = "") Oddnumber arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "updateOddnum", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.UpdateOddnum")
	@ResponseWrapper(localName = "updateOddnumResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.UpdateOddnumResponse")
	public String updateOddnum(@WebParam(name = "arg0", targetNamespace = "") Oddnumber arg0);

	/**
	 * 
	 * @param arg0
	 * @return returns com.service.service.impl.Oddnumber
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "getUseOddnumber", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.GetUseOddnumber")
	@ResponseWrapper(localName = "getUseOddnumberResponse", targetNamespace = "http://impl.service.service.com/", className = "com.service.service.impl.GetUseOddnumberResponse")
	public Oddnumber getUseOddnumber(@WebParam(name = "arg0", targetNamespace = "") String arg0);

}
