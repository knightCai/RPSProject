
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for deleteOddnum complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="deleteOddnum">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Oddnum" type="{http://service.service.com/}oddnumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteOddnum", propOrder = { "oddnum" })
public class DeleteOddnum {

	@XmlElement(name = "Oddnum")
	protected Oddnumber oddnum;

	/**
	 * Gets the value of the oddnum property.
	 * 
	 * @return possible object is {@link Oddnumber }
	 * 
	 */
	public Oddnumber getOddnum() {
		return oddnum;
	}

	/**
	 * Sets the value of the oddnum property.
	 * 
	 * @param value
	 *            allowed object is {@link Oddnumber }
	 * 
	 */
	public void setOddnum(Oddnumber value) {
		this.oddnum = value;
	}

}
