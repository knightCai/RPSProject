
package com.service.service.impl.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findAllOddnumResponse", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllOddnumResponse", namespace = "http://impl.service.service.com/")
public class FindAllOddnumResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.service.model.Oddnumber> _return;

    /**
     * 
     * @return
     *     returns List<Oddnumber>
     */
    public List<com.service.model.Oddnumber> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.service.model.Oddnumber> _return) {
        this._return = _return;
    }

}
