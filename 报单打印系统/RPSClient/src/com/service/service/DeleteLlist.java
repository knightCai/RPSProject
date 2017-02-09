
package com.service.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for deleteLlist complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="deleteLlist">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Llist" type="{http://service.service.com/}logisticslisting" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteLlist", propOrder = { "llist" })
public class DeleteLlist {

	@XmlElement(name = "Llist")
	protected Logisticslisting llist;

	/**
	 * Gets the value of the llist property.
	 * 
	 * @return possible object is {@link Logisticslisting }
	 * 
	 */
	public Logisticslisting getLlist() {
		return llist;
	}

	/**
	 * Sets the value of the llist property.
	 * 
	 * @param value
	 *            allowed object is {@link Logisticslisting }
	 * 
	 */
	public void setLlist(Logisticslisting value) {
		this.llist = value;
	}

}
