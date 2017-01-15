
package com.service.service.impl.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findLlistByParamsResponse", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findLlistByParamsResponse", namespace = "http://impl.service.service.com/")
public class FindLlistByParamsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.service.model.Logisticslisting> _return;

    /**
     * 
     * @return
     *     returns List<Logisticslisting>
     */
    public List<com.service.model.Logisticslisting> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.service.model.Logisticslisting> _return) {
        this._return = _return;
    }

}
