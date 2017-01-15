
package com.service.service.impl.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findUserByUserIdResponse", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findUserByUserIdResponse", namespace = "http://impl.service.service.com/")
public class FindUserByUserIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.service.model.Userinfo _return;

    /**
     * 
     * @return
     *     returns Userinfo
     */
    public com.service.model.Userinfo getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.service.model.Userinfo _return) {
        this._return = _return;
    }

}
