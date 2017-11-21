package com.IDBWebApp;

import java.util.List;

public class IBartenderEndpoints {
    public class IBartender {
        public String name;
        public int[] early_avgs = new int[] {0,0,0,0,0,0,0};    // every index corresponds to an avg for that day of the week
        public int[] late_avgs = new int[] {0,0,0,0,0,0,0};
        public int[] total_avgs = new int[] {0,0,0,0,0,0,0};
    }
    public class IBartenderResult{
        public List<IBartender> bartenders;
    }
}
