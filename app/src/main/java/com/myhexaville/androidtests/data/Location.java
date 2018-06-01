package com.myhexaville.androidtests.data;

import android.util.Log;

public class Location {
    private final static String TAG = "LEE: <" + Location.class.getSimpleName() + ">";

    private String cityName;
    private double latitude, longitude;

    public Location() {
        Log.v(TAG, "Location");
    }

    public Location(String cityName, double latitude, double longitude) {
        Log.v(TAG, "Location city lat long");
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCityName() {
        Log.v(TAG, "getCityName");
        return cityName;
    }

    public void setCityName(String cityName) {
        Log.v(TAG, "setCityName");
        this.cityName = cityName;
    }

    public double getLatitude() {
        Log.v(TAG, "getLatitude");
        return latitude;
    }

    public void setLatitude(double latitude) {
        Log.v(TAG, "setLatitude");
        this.latitude = latitude;
    }

    public double getLongitude() {
        Log.v(TAG, "getLongitude");
        return longitude;
    }

    public void setLongitude(double longitude) {
        Log.v(TAG, "setLongitude");
        this.longitude = longitude;
    }
}
