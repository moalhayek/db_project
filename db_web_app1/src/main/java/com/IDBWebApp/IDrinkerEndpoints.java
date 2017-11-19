package com.IDBWebApp;

import java.util.List;

public class IDrinkerEndpoints {
    public class IDrinker {
        public String name;
    }
    public class IDrinkerResult {
        public IDrinker drinker;
    }
    public class IAgeEarnings {
        public String age;
        public String averageEarning;
    }
    public class IAgeEarningsResult {
        public List<IAgeEarnings> drinkersAgeGroups;
    }
}
