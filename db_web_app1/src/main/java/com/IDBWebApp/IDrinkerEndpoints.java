package com.IDBWebApp;

import java.util.List;

public class IDrinkerEndpoints {
    public class IDrinker {
        public String name;
        public int spendingPerNight;
        public String expensiveFavBeer;
    }
    public class IDrinkerResult {
        public List<IDrinker> drinkers;
    }
    public class IAgeEarnings {
        public String age;
        public String averageEarning;
    }
    public class IAgeEarningsResult {
        public List<IAgeEarnings> drinkersAgeGroups;
    }
}
