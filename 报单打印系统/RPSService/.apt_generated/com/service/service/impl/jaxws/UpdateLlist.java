
package com.service.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "updateLlist", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateLlist", namespace = "http://impl.service.service.com/")
public class UpdateLlist {

    @XmlElement(name = "arg0", namespace = "")
    private com.service.model.Logisticslisting arg0;

    /**
     * 
     * @return
     *     returns Logisticslisting
     */
    public com.service.model.Logisticslisting getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.service.model.Logisticslisting arg0) {
        this.arg0 = arg0;
    }

}
