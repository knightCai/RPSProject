
package com.service.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findLlistByPkIdResponse", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findLlistByPkIdResponse", namespace = "http://impl.service.service.com/")
public class FindLlistByPkIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.service.model.Logisticslisting _return;

    /**
     * 
     * @return
     *     returns Logisticslisting
     */
    public com.service.model.Logisticslisting getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.service.model.Logisticslisting _return) {
        this._return = _return;
    }

}
