
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for updateLlistBySql complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="updateLlistBySql">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="param" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateLlistBySql", propOrder = { "param" })
public class UpdateLlistBySql {

	protected String param;

	/**
	 * Gets the value of the param property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParam() {
		return param;
	}

	/**
	 * Sets the value of the param property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParam(String value) {
		this.param = value;
	}

}
