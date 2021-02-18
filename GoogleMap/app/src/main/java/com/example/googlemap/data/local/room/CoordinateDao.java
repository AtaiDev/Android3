package com.example.googlemap.data.local.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.googlemap.data.local.models.LatLngTab;


import java.util.List;


@Dao
public interface CoordinateDao {

    @Query("Select * From LatLngTab")
    List<LatLngTab> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLatLng(LatLngTab latLng);

    @Delete
    void deleteLatLng(LatLngTab latLng);


}
