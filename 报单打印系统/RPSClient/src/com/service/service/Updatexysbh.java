
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updatexysbh complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="updatexysbh">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xysbh" type="{http://service.service.com/}xysbhparam" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updatexysbh", propOrder = { "xysbh" })
public class Updatexysbh {

	protected Xysbhparam xysbh;

	/**
	 * Gets the value of the xysbh property.
	 * 
	 * @return possible object is {@link Xysbhparam }
	 * 
	 */
	public Xysbhparam getXysbh() {
		return xysbh;
	}

	/**
	 * Sets the value of the xysbh property.
	 * 
	 * @param value
	 *            allowed object is {@link Xysbhparam }
	 * 
	 */
	public void setXysbh(Xysbhparam value) {
		this.xysbh = value;
	}

}
