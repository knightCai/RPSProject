
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findXysbhByParamname complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findXysbhByParamname">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paramname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findXysbhByParamname", propOrder = { "paramname" })
public class FindXysbhByParamname {

	protected String paramname;

	/**
	 * Gets the value of the paramname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getParamname() {
		return paramname;
	}

	/**
	 * Sets the value of the paramname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setParamname(String value) {
		this.paramname = value;
	}

}
