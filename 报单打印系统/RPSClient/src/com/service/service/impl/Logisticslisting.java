
package com.service.service.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for logisticslisting complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="logisticslisting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargoid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargoname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cargotype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneeaddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneecountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consigneraddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consignercardid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consignercountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consignername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="consignerphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="declarenum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="declareprice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="declarepricesum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="declareweight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expressnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="importuser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isprint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="legalunit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="netweight" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packagecount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pkid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "logisticslisting", propOrder = { "brand", "cargoid", "cargoname", "cargotype", "consigneeaddr",
		"consigneecountry", "consigneename", "consigneephone", "consigneraddr", "consignercardid", "consignercountry",
		"consignername", "consignerphone", "count", "createtime", "declarenum", "declareprice", "declarepricesum",
		"declareweight", "expressnum", "importnum", "importuser", "isprint", "legalnum", "legalunit", "netweight",
		"packagecount", "pkid", "serialnum" })
public class Logisticslisting {

	protected String brand;
	protected String cargoid;
	protected String cargoname;
	protected String cargotype;
	protected String consigneeaddr;
	protected String consigneecountry;
	protected String consigneename;
	protected String consigneephone;
	protected String consigneraddr;
	protected String consignercardid;
	protected String consignercountry;
	protected String consignername;
	protected String consignerphone;
	protected String count;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createtime;
	protected String declarenum;
	protected String declareprice;
	protected String declarepricesum;
	protected String declareweight;
	protected String expressnum;
	protected String importnum;
	protected String importuser;
	protected String isprint;
	protected String legalnum;
	protected String legalunit;
	protected String netweight;
	protected String packagecount;
	protected String pkid;
	protected String serialnum;

	/**
	 * Gets the value of the brand property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the value of the brand property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBrand(String value) {
		this.brand = value;
	}

	/**
	 * Gets the value of the cargoid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCargoid() {
		return cargoid;
	}

	/**
	 * Sets the value of the cargoid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCargoid(String value) {
		this.cargoid = value;
	}

	/**
	 * Gets the value of the cargoname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCargoname() {
		return cargoname;
	}

	/**
	 * Sets the value of the cargoname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCargoname(String value) {
		this.cargoname = value;
	}

	/**
	 * Gets the value of the cargotype property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCargotype() {
		return cargotype;
	}

	/**
	 * Sets the value of the cargotype property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCargotype(String value) {
		this.cargotype = value;
	}

	/**
	 * Gets the value of the consigneeaddr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneeaddr() {
		return consigneeaddr;
	}

	/**
	 * Sets the value of the consigneeaddr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneeaddr(String value) {
		this.consigneeaddr = value;
	}

	/**
	 * Gets the value of the consigneecountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneecountry() {
		return consigneecountry;
	}

	/**
	 * Sets the value of the consigneecountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneecountry(String value) {
		this.consigneecountry = value;
	}

	/**
	 * Gets the value of the consigneename property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneename() {
		return consigneename;
	}

	/**
	 * Sets the value of the consigneename property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneename(String value) {
		this.consigneename = value;
	}

	/**
	 * Gets the value of the consigneephone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneephone() {
		return consigneephone;
	}

	/**
	 * Sets the value of the consigneephone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneephone(String value) {
		this.consigneephone = value;
	}

	/**
	 * Gets the value of the consigneraddr property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsigneraddr() {
		return consigneraddr;
	}

	/**
	 * Sets the value of the consigneraddr property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsigneraddr(String value) {
		this.consigneraddr = value;
	}

	/**
	 * Gets the value of the consignercardid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsignercardid() {
		return consignercardid;
	}

	/**
	 * Sets the value of the consignercardid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsignercardid(String value) {
		this.consignercardid = value;
	}

	/**
	 * Gets the value of the consignercountry property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsignercountry() {
		return consignercountry;
	}

	/**
	 * Sets the value of the consignercountry property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsignercountry(String value) {
		this.consignercountry = value;
	}

	/**
	 * Gets the value of the consignername property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsignername() {
		return consignername;
	}

	/**
	 * Sets the value of the consignername property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsignername(String value) {
		this.consignername = value;
	}

	/**
	 * Gets the value of the consignerphone property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getConsignerphone() {
		return consignerphone;
	}

	/**
	 * Sets the value of the consignerphone property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setConsignerphone(String value) {
		this.consignerphone = value;
	}

	/**
	 * Gets the value of the count property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCount() {
		return count;
	}

	/**
	 * Sets the value of the count property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCount(String value) {
		this.count = value;
	}

	/**
	 * Gets the value of the createtime property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getCreatetime() {
		return createtime;
	}

	/**
	 * Sets the value of the createtime property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setCreatetime(XMLGregorianCalendar value) {
		this.createtime = value;
	}

	/**
	 * Gets the value of the declarenum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeclarenum() {
		return declarenum;
	}

	/**
	 * Sets the value of the declarenum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeclarenum(String value) {
		this.declarenum = value;
	}

	/**
	 * Gets the value of the declareprice property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeclareprice() {
		return declareprice;
	}

	/**
	 * Sets the value of the declareprice property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeclareprice(String value) {
		this.declareprice = value;
	}

	/**
	 * Gets the value of the declarepricesum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeclarepricesum() {
		return declarepricesum;
	}

	/**
	 * Sets the value of the declarepricesum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeclarepricesum(String value) {
		this.declarepricesum = value;
	}

	/**
	 * Gets the value of the declareweight property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDeclareweight() {
		return declareweight;
	}

	/**
	 * Sets the value of the declareweight property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDeclareweight(String value) {
		this.declareweight = value;
	}

	/**
	 * Gets the value of the expressnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getExpressnum() {
		return expressnum;
	}

	/**
	 * Sets the value of the expressnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setExpressnum(String value) {
		this.expressnum = value;
	}

	/**
	 * Gets the value of the importnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImportnum() {
		return importnum;
	}

	/**
	 * Sets the value of the importnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImportnum(String value) {
		this.importnum = value;
	}

	/**
	 * Gets the value of the importuser property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getImportuser() {
		return importuser;
	}

	/**
	 * Sets the value of the importuser property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setImportuser(String value) {
		this.importuser = value;
	}

	/**
	 * Gets the value of the isprint property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIsprint() {
		return isprint;
	}

	/**
	 * Sets the value of the isprint property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIsprint(String value) {
		this.isprint = value;
	}

	/**
	 * Gets the value of the legalnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLegalnum() {
		return legalnum;
	}

	/**
	 * Sets the value of the legalnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLegalnum(String value) {
		this.legalnum = value;
	}

	/**
	 * Gets the value of the legalunit property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLegalunit() {
		return legalunit;
	}

	/**
	 * Sets the value of the legalunit property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLegalunit(String value) {
		this.legalunit = value;
	}

	/**
	 * Gets the value of the netweight property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNetweight() {
		return netweight;
	}

	/**
	 * Sets the value of the netweight property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNetweight(String value) {
		this.netweight = value;
	}

	/**
	 * Gets the value of the packagecount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPackagecount() {
		return packagecount;
	}

	/**
	 * Sets the value of the packagecount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPackagecount(String value) {
		this.packagecount = value;
	}

	/**
	 * Gets the value of the pkid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPkid() {
		return pkid;
	}

	/**
	 * Sets the value of the pkid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPkid(String value) {
		this.pkid = value;
	}

	/**
	 * Gets the value of the serialnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSerialnum() {
		return serialnum;
	}

	/**
	 * Sets the value of the serialnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSerialnum(String value) {
		this.serialnum = value;
	}

}
