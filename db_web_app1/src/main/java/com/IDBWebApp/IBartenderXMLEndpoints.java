package com.IDBWebApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IBartenderXMLEndpoints {
    @XmlElement public int barId;
    @XmlElement public String name;
    @XmlElement public int age;
    @XmlElement public String gender;
    @XmlElement public String street_address;
    @XmlElement public String city;
    @XmlElement public String state;
    @XmlElement public String startDate;
}
