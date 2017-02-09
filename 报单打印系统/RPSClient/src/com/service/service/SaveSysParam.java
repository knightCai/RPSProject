
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for saveSysParam complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="saveSysParam">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sys" type="{http://service.service.com/}sysparam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveSysParam", propOrder = { "sys" })
public class SaveSysParam {

	protected Sysparam sys;

	/**
	 * Gets the value of the sys property.
	 * 
	 * @return possible object is {@link Sysparam }
	 * 
	 */
	public Sysparam getSys() {
		return sys;
	}

	/**
	 * Sets the value of the sys property.
	 * 
	 * @param value
	 *            allowed object is {@link Sysparam }
	 * 
	 */
	public void setSys(Sysparam value) {
		this.sys = value;
	}

}
