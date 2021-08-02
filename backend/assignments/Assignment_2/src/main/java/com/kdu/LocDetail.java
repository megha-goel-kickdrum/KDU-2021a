package com.kdu;


public class LocDetail {
    private String latitude;
    private String longitude;
    private String address;
    private boolean locationFound = false;

    public void findLatLong(String lat, String lng, String addr, boolean found )
    {
        this.latitude = lat;
        this.longitude = lng;
        this.address = addr;
        this.locationFound = found;
    }

    public String getLatitude()
    {
        return this.latitude;
    }

    public String getLongitude()
    {
        return  this.longitude;
    }

    public String getAddress()
    {
        return this.address;
    }

    public boolean isLocationFound()
    {
        return this.locationFound;
    }

    public String getLatLong()
    {
        return  "Latitude : "+this.latitude+";  Longitude : "+this.longitude + ";  Address : "+this.address;

    }



}
