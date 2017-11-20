package com.IDBWebApp;

import java.util.Date;
import java.util.List;

public class IAdEndpoints {
    public class IAdPurchase {
        public int barId;
        public String platformName;
        public Date startDate;
        public Date endDate;
        public int totalCost;
        public int totalClicks;
        public double costPerClick;
    }
    public class IAdPurchaseResult{
        public List<IAdPurchase> adPurchases;
    }
}
