package com.IDBWebApp;

import java.util.List;

public class ITransactionsEndpoints {
    public class IEarning {
        public String month;
        public String year;
        public int early_earnings;
        public int late_earnings;
        public int total_earnings;
    }

    public class IEarningsResult {
        public List<IEarning> monthly_earnings;
    }

    public class IDailyAverages {
        public int dayOfWeek;
        public int avg_early_earnings;
        public int avg_late_earnings;
        public int total_avg_earnings;
    }

    public class IDailyAveragesResult {
        public List<IDailyAverages> dailyAverages;
    }
}

