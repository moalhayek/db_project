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
        public double profitPerBottle;
        public int totalSold;
        public double totalProfit;

    }
    public class IBeerResult{
        public List<IBeer> beers;
    }

    public class IBeer2{
        public String name;
        public String manuf;
        public int id;
    }

    public class IBeer2Result{
        public List<IBeer2> beers2;
    }
}
