package com.example.googlemap.data.local.room;

import androidx.room.Dao;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.googlemap.data.local.models.LatLngCord;
import com.google.android.gms.maps.model.LatLng;



import java.util.List;


@Dao
public interface CoordinateDao {

    @Query("Select * From LatLngCord")
    List<LatLngCord> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLatLng(LatLngCord latLng);

    @Query("DELETE FROM LatLngCord Where latLng= :latLngDelete" )
    void deleteLatLng(LatLng latLngDelete);

    @Query("DELETE From LatLngCord")
    void  deleteAll();

}
