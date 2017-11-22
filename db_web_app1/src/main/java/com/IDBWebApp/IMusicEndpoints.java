package com.IDBWebApp;

import java.util.List;

public class IMusicEndpoints {
    //music objects
    public class IMusic {
        public String genre;
        public int listeners;
    }
    public class IMusicResult{
        public List<IMusic> musicData;
    }
}
