
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findLlistByPkId complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findLlistByPkId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pkId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findLlistByPkId", propOrder = { "pkId" })
public class FindLlistByPkId {

	protected String pkId;

	/**
	 * Gets the value of the pkId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPkId() {
		return pkId;
	}

	/**
	 * Sets the value of the pkId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPkId(String value) {
		this.pkId = value;
	}

}
