
package com.service.service.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateOddnum complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="updateOddnum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://impl.service.service.com/}oddnumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateOddnum", propOrder = { "arg0" })
public class UpdateOddnum {

	protected Oddnumber arg0;

	/**
	 * Gets the value of the arg0 property.
	 * 
	 * @return possible object is {@link Oddnumber }
	 * 
	 */
	public Oddnumber getArg0() {
		return arg0;
	}

	/**
	 * Sets the value of the arg0 property.
	 * 
	 * @param value
	 *            allowed object is {@link Oddnumber }
	 * 
	 */
	public void setArg0(Oddnumber value) {
		this.arg0 = value;
	}

}
