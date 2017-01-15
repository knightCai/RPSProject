
package com.service.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "saveOddnum", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveOddnum", namespace = "http://impl.service.service.com/")
public class SaveOddnum {

    @XmlElement(name = "arg0", namespace = "")
    private com.service.model.Oddnumber arg0;

    /**
     * 
     * @return
     *     returns Oddnumber
     */
    public com.service.model.Oddnumber getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.service.model.Oddnumber arg0) {
        this.arg0 = arg0;
    }

}
