package com.IDBWebApp;

import java.util.List;

public class IBeerEndpoints {
    public class IBeer {
        public String name;
        public String manuf;
        public boolean isOnTap;
        public double abv;
        public int salePrice;
        public double manuf_price;
        public double profit;
    }
    public class IBeerResult{
        public List<IBeer> beers;
    }
}
