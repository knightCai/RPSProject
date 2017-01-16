
package com.service.service.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findLlistByPkIdResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findLlistByPkIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://impl.service.service.com/}logisticslisting" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findLlistByPkIdResponse", propOrder = { "_return" })
public class FindLlistByPkIdResponse {

	@XmlElement(name = "return")
	protected Logisticslisting _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Logisticslisting }
	 * 
	 */
	public Logisticslisting getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Logisticslisting }
	 * 
	 */
	public void setReturn(Logisticslisting value) {
		this._return = value;
	}

}
