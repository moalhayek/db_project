package com.IDBWebApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IDeleteBarEndpoint {
    @XmlElement public int id;
    @XmlElement public String name;
}
