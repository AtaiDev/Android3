package com.example.googlemap.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.googlemap.data.local.models.LatLngTab;
import com.example.googlemap.data.local.room.CoordinateDao;


@Database(entities = {LatLngTab.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract CoordinateDao coordinateDao();

}
