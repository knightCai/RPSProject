
package com.service.service.impl.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "saveBatch", namespace = "http://impl.service.service.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saveBatch", namespace = "http://impl.service.service.com/")
public class SaveBatch {

    @XmlElement(name = "arg0", namespace = "")
    private List<com.service.model.Logisticslisting> arg0;

    /**
     * 
     * @return
     *     returns List<Logisticslisting>
     */
    public List<com.service.model.Logisticslisting> getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(List<com.service.model.Logisticslisting> arg0) {
        this.arg0 = arg0;
    }

}
