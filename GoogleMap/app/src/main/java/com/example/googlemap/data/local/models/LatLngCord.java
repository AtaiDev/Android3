package com.example.googlemap.data.local.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.google.android.gms.maps.model.LatLng;

@Entity

public class LatLngCord {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private LatLng latLng;

    public LatLngCord(LatLng latLng) {
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        return "LatLngCord{" +
                "latLng=" + latLng +
                '}';
    }
}
