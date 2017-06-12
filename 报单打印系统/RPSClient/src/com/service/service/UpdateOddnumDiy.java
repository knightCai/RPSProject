
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateOddnumDiy complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="updateOddnumDiy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Oddnum" type="{http://service.service.com/}oddnumerDiy" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateOddnumDiy", propOrder = { "oddnum" })
public class UpdateOddnumDiy {

	@XmlElement(name = "Oddnum")
	protected OddnumerDiy oddnum;

	/**
	 * Gets the value of the oddnum property.
	 * 
	 * @return possible object is {@link OddnumerDiy }
	 * 
	 */
	public OddnumerDiy getOddnum() {
		return oddnum;
	}

	/**
	 * Sets the value of the oddnum property.
	 * 
	 * @param value
	 *            allowed object is {@link OddnumerDiy }
	 * 
	 */
	public void setOddnum(OddnumerDiy value) {
		this.oddnum = value;
	}

}
