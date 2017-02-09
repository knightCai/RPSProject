
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for xysbhparam complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="xysbhparam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paramname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramnum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pkid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sernum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "xysbhparam", propOrder = { "paramname", "paramnum", "pkid", "sernum", "unit" })
public class Xysbhparam {

	protected String paramname;
	protected String paramnum;
	protected String pkid;
	protected int sernum;
	protected String unit;

	/**
	 * Gets the value of the paramname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * Sets the value of the paramname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParamname(String value) {
		this.paramname = value;
	}

	/**
	 * Gets the value of the paramnum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParamnum() {
		return paramnum;
	}

	/**
	 * Sets the value of the paramnum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParamnum(String value) {
		this.paramnum = value;
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
	 * Gets the value of the sernum property.
	 * 
	 */
	public int getSernum() {
		return sernum;
	}

	/**
	 * Sets the value of the sernum property.
	 * 
	 */
	public void setSernum(int value) {
		this.sernum = value;
	}

	/**
	 * Gets the value of the unit property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the value of the unit property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUnit(String value) {
		this.unit = value;
	}

}
