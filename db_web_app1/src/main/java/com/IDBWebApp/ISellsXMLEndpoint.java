package com.IDBWebApp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ISellsXMLEndpoint {
    @XmlElement public int bar_id;
    @XmlElement public int beer_id;
    @XmlElement public int is_on_tap;
    @XmlElement public int price;
}
