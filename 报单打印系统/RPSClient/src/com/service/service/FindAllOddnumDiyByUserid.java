
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for findAllOddnumDiyByUserid complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="findAllOddnumDiyByUserid">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllOddnumDiyByUserid", propOrder = { "userid" })
public class FindAllOddnumDiyByUserid {

	protected String userid;

	/**
	 * Gets the value of the userid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * Sets the value of the userid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserid(String value) {
		this.userid = value;
	}

}
