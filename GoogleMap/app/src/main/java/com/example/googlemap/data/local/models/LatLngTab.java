package com.example.googlemap.data.local.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LatLngTab {

    @PrimaryKey
    public int id;

    private double latitude;

    private double longitude;

    public LatLngTab(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }



    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
