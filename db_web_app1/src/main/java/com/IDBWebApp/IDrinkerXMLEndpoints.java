package com.IDBWebApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IDrinkerXMLEndpoints {
    @XmlElement public String name;
    @XmlElement public int age;
    @XmlElement public String gender;
    @XmlElement public String street_address;
    @XmlElement public String city;
    @XmlElement public String state;
    @XmlElement public int salary;
    @XmlElement public int spending_per_night;
    @XmlElement public String crowding_pref;
    @XmlElement public String relationship_status;
}
