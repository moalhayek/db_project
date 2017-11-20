package com.IDBWebApp;

import java.util.List;

public class ITransactionsEndpoints {
    public class IEarning {
        public String month;
        public String year;
        public int earnings;
    }

    public class IEarningsResult {
        public List<IEarning> monthly_earnings;
    }
}
