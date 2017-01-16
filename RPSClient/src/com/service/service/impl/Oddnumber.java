
package com.service.service.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for oddnumber complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="oddnumber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amountnum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="createtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="endnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pkid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suplusnum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="usenum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warnnum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "oddnumber", propOrder = { "amountnum", "createtime", "endnum", "pkid", "startnum", "state",
		"suplusnum", "usenum", "userid", "warnnum" })
public class Oddnumber {

	protected int amountnum;
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar createtime;
	protected String endnum;
	protected String pkid;
	protected String startnum;
	protected String state;
	protected int suplusnum;
	protected String usenum;
	protected String userid;
	protected int warnnum;

	/**
	 * Gets the value of the amountnum property.
	 * 
	 */
	public int getAmountnum() {
		return amountnum;
	}

	/**
	 * Sets the value of the amountnum property.
	 * 
	 */
	public void setAmountnum(int value) {
		this.amountnum = value;
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
	 * Gets the value of the endnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEndnum() {
		return endnum;
	}

	/**
	 * Sets the value of the endnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEndnum(String value) {
		this.endnum = value;
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
	 * Gets the value of the startnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStartnum() {
		return startnum;
	}

	/**
	 * Sets the value of the startnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStartnum(String value) {
		this.startnum = value;
	}

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setState(String value) {
		this.state = value;
	}

	/**
	 * Gets the value of the suplusnum property.
	 * 
	 */
	public int getSuplusnum() {
		return suplusnum;
	}

	/**
	 * Sets the value of the suplusnum property.
	 * 
	 */
	public void setSuplusnum(int value) {
		this.suplusnum = value;
	}

	/**
	 * Gets the value of the usenum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUsenum() {
		return usenum;
	}

	/**
	 * Sets the value of the usenum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUsenum(String value) {
		this.usenum = value;
	}

	/**
	 * Gets the value of the userid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the value of the userid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserid(String value) {
		this.userid = value;
	}

	/**
	 * Gets the value of the warnnum property.
	 * 
	 */
	public int getWarnnum() {
		return warnnum;
	}

	/**
	 * Sets the value of the warnnum property.
	 * 
	 */
	public void setWarnnum(int value) {
		this.warnnum = value;
	}

}
