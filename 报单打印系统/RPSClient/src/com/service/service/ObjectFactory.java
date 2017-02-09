
package com.service.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.service.service package.
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

	private final static QName _FindAllSysParam_QNAME = new QName("http://service.service.com/", "findAllSysParam");
	private final static QName _FindSysByParamname_QNAME = new QName("http://service.service.com/",
			"findSysByParamname");
	private final static QName _DeleteSysParamResponse_QNAME = new QName("http://service.service.com/",
			"deleteSysParamResponse");
	private final static QName _FindSysParamByPkIdResponse_QNAME = new QName("http://service.service.com/",
			"findSysParamByPkIdResponse");
	private final static QName _FindSysByParamnameResponse_QNAME = new QName("http://service.service.com/",
			"findSysByParamnameResponse");
	private final static QName _SaveSysParam_QNAME = new QName("http://service.service.com/", "saveSysParam");
	private final static QName _UpdateSysParamResponse_QNAME = new QName("http://service.service.com/",
			"updateSysParamResponse");
	private final static QName _DeleteSysParam_QNAME = new QName("http://service.service.com/", "deleteSysParam");
	private final static QName _FindSysParamByPkId_QNAME = new QName("http://service.service.com/",
			"findSysParamByPkId");
	private final static QName _SaveSysParamResponse_QNAME = new QName("http://service.service.com/",
			"saveSysParamResponse");
	private final static QName _FindAllSysParamResponse_QNAME = new QName("http://service.service.com/",
			"findAllSysParamResponse");
	private final static QName _UpdateSysParam_QNAME = new QName("http://service.service.com/", "updateSysParam");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.service.service
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link FindSysParamByPkId }
	 * 
	 */
	public FindSysParamByPkId createFindSysParamByPkId() {
		return new FindSysParamByPkId();
	}

	/**
	 * Create an instance of {@link SaveSysParam }
	 * 
	 */
	public SaveSysParam createSaveSysParam() {
		return new SaveSysParam();
	}

	/**
	 * Create an instance of {@link UpdateSysParamResponse }
	 * 
	 */
	public UpdateSysParamResponse createUpdateSysParamResponse() {
		return new UpdateSysParamResponse();
	}

	/**
	 * Create an instance of {@link SaveSysParamResponse }
	 * 
	 */
	public SaveSysParamResponse createSaveSysParamResponse() {
		return new SaveSysParamResponse();
	}

	/**
	 * Create an instance of {@link DeleteSysParam }
	 * 
	 */
	public DeleteSysParam createDeleteSysParam() {
		return new DeleteSysParam();
	}

	/**
	 * Create an instance of {@link FindSysByParamname }
	 * 
	 */
	public FindSysByParamname createFindSysByParamname() {
		return new FindSysByParamname();
	}

	/**
	 * Create an instance of {@link UpdateSysParam }
	 * 
	 */
	public UpdateSysParam createUpdateSysParam() {
		return new UpdateSysParam();
	}

	/**
	 * Create an instance of {@link FindAllSysParamResponse }
	 * 
	 */
	public FindAllSysParamResponse createFindAllSysParamResponse() {
		return new FindAllSysParamResponse();
	}

	/**
	 * Create an instance of {@link FindAllSysParam }
	 * 
	 */
	public FindAllSysParam createFindAllSysParam() {
		return new FindAllSysParam();
	}

	/**
	 * Create an instance of {@link DeleteSysParamResponse }
	 * 
	 */
	public DeleteSysParamResponse createDeleteSysParamResponse() {
		return new DeleteSysParamResponse();
	}

	/**
	 * Create an instance of {@link Sysparam }
	 * 
	 */
	public Sysparam createSysparam() {
		return new Sysparam();
	}

	/**
	 * Create an instance of {@link FindSysParamByPkIdResponse }
	 * 
	 */
	public FindSysParamByPkIdResponse createFindSysParamByPkIdResponse() {
		return new FindSysParamByPkIdResponse();
	}

	/**
	 * Create an instance of {@link FindSysByParamnameResponse }
	 * 
	 */
	public FindSysByParamnameResponse createFindSysByParamnameResponse() {
		return new FindSysByParamnameResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FindAllSysParam
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findAllSysParam")
	public JAXBElement<FindAllSysParam> createFindAllSysParam(FindAllSysParam value) {
		return new JAXBElement<FindAllSysParam>(_FindAllSysParam_QNAME, FindAllSysParam.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindSysByParamname }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findSysByParamname")
	public JAXBElement<FindSysByParamname> createFindSysByParamname(FindSysByParamname value) {
		return new JAXBElement<FindSysByParamname>(_FindSysByParamname_QNAME, FindSysByParamname.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link DeleteSysParamResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "deleteSysParamResponse")
	public JAXBElement<DeleteSysParamResponse> createDeleteSysParamResponse(DeleteSysParamResponse value) {
		return new JAXBElement<DeleteSysParamResponse>(_DeleteSysParamResponse_QNAME, DeleteSysParamResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindSysParamByPkIdResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findSysParamByPkIdResponse")
	public JAXBElement<FindSysParamByPkIdResponse> createFindSysParamByPkIdResponse(FindSysParamByPkIdResponse value) {
		return new JAXBElement<FindSysParamByPkIdResponse>(_FindSysParamByPkIdResponse_QNAME,
				FindSysParamByPkIdResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindSysByParamnameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findSysByParamnameResponse")
	public JAXBElement<FindSysByParamnameResponse> createFindSysByParamnameResponse(FindSysByParamnameResponse value) {
		return new JAXBElement<FindSysByParamnameResponse>(_FindSysByParamnameResponse_QNAME,
				FindSysByParamnameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveSysParam }
	 * {@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "saveSysParam")
	public JAXBElement<SaveSysParam> createSaveSysParam(SaveSysParam value) {
		return new JAXBElement<SaveSysParam>(_SaveSysParam_QNAME, SaveSysParam.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link UpdateSysParamResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "updateSysParamResponse")
	public JAXBElement<UpdateSysParamResponse> createUpdateSysParamResponse(UpdateSysParamResponse value) {
		return new JAXBElement<UpdateSysParamResponse>(_UpdateSysParamResponse_QNAME, UpdateSysParamResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link DeleteSysParam
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "deleteSysParam")
	public JAXBElement<DeleteSysParam> createDeleteSysParam(DeleteSysParam value) {
		return new JAXBElement<DeleteSysParam>(_DeleteSysParam_QNAME, DeleteSysParam.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindSysParamByPkId }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findSysParamByPkId")
	public JAXBElement<FindSysParamByPkId> createFindSysParamByPkId(FindSysParamByPkId value) {
		return new JAXBElement<FindSysParamByPkId>(_FindSysParamByPkId_QNAME, FindSysParamByPkId.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveSysParamResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "saveSysParamResponse")
	public JAXBElement<SaveSysParamResponse> createSaveSysParamResponse(SaveSysParamResponse value) {
		return new JAXBElement<SaveSysParamResponse>(_SaveSysParamResponse_QNAME, SaveSysParamResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link FindAllSysParamResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "findAllSysParamResponse")
	public JAXBElement<FindAllSysParamResponse> createFindAllSysParamResponse(FindAllSysParamResponse value) {
		return new JAXBElement<FindAllSysParamResponse>(_FindAllSysParamResponse_QNAME, FindAllSysParamResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSysParam
	 * }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://service.service.com/", name = "updateSysParam")
	public JAXBElement<UpdateSysParam> createUpdateSysParam(UpdateSysParam value) {
		return new JAXBElement<UpdateSysParam>(_UpdateSysParam_QNAME, UpdateSysParam.class, null, value);
	}

}
