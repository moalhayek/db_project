package com.IDBWebApp;

import java.util.List;

public class IBartenderEndpoints {
    public class IBartender {
        public int bar_id;
        public int bartender_id;
        public String shift_name;
        public int bartender_sales;
    }
    public class IBartenderResult{
        public List<IBartender> topBartenders;
    }
}
