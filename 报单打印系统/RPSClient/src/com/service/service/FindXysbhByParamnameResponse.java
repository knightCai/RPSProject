
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findXysbhByParamnameResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findXysbhByParamnameResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.service.com/}xysbhparam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findXysbhByParamnameResponse", propOrder = { "_return" })
public class FindXysbhByParamnameResponse {

	@XmlElement(name = "return")
	protected Xysbhparam _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link Xysbhparam }
	 * 
	 */
	public Xysbhparam getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link Xysbhparam }
	 * 
	 */
	public void setReturn(Xysbhparam value) {
		this._return = value;
	}

}
