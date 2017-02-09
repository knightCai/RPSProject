
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findSysParamByPkIdResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findSysParamByPkIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.service.com/}sysparam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findSysParamByPkIdResponse", propOrder = { "_return" })
public class FindSysParamByPkIdResponse {

	@XmlElement(name = "return")
	protected Sysparam _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Sysparam }
	 * 
	 */
	public Sysparam getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Sysparam }
	 * 
	 */
	public void setReturn(Sysparam value) {
		this._return = value;
	}

}
