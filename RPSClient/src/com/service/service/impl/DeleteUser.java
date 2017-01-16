
package com.service.service.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for deleteUser complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="deleteUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://impl.service.service.com/}userinfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteUser", propOrder = { "user" })
public class DeleteUser {

	protected Userinfo user;

	/**
	 * Gets the value of the user property.
	 * 
	 * @return possible object is {@link Userinfo }
	 * 
	 */
	public Userinfo getUser() {
		return user;
	}

	/**
	 * Sets the value of the user property.
	 * 
	 * @param value
	 *            allowed object is {@link Userinfo }
	 * 
	 */
	public void setUser(Userinfo value) {
		this.user = value;
	}

}