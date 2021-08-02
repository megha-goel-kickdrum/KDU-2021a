package com.kdu;
//import com.kdu.Location;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    public static final HashMap<String, LocDetail> locationCache = new HashMap<String, LocDetail>();

    public Map<String, LocDetail> getLocationCache(){
        return this.locationCache;
    }

    public LocDetail getLocationFromCache(String addressPoint){
        if(!this.locationCache.isEmpty() && this.locationCache.containsKey(addressPoint))
        {
            return  this.getLocationCache().get(addressPoint);


        }
        return new LocDetail();
    }

    public void putLocationInCache(String addressPoint, LocDetail location){
        this.locationCache.put(addressPoint,location);
    }
}
