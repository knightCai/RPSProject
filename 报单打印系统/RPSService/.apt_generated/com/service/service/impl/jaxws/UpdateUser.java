
package com.service.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateUser", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateUser", namespace = "http://impl.service.service.com/")
public class UpdateUser {

    @XmlElement(name = "arg0", namespace = "")
    private com.service.model.Userinfo arg0;

    /**
     * 
     * @return
     *     returns Userinfo
     */
    public com.service.model.Userinfo getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.service.model.Userinfo arg0) {
        this.arg0 = arg0;
    }

}
