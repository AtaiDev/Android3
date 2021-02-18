package com.example.googlemap.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverters;


import com.example.googlemap.converter.Converter;
import com.example.googlemap.data.local.models.LatLngCord;
import com.example.googlemap.data.local.room.CoordinateDao;


@Database(entities = {LatLngCord.class}, version = 3)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context){
        if (appDatabase == null){
            appDatabase = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "database-room")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public abstract CoordinateDao coordinateDao();

}
