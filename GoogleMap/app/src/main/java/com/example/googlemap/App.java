package com.example.googlemap;

import android.app.Application;



import androidx.room.Room;

import com.example.googlemap.data.AppDatabase;


public class App extends Application {

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        if (database == null) {
            database = Room
                    .databaseBuilder(
                            getApplicationContext(),
                            AppDatabase.class,
                            "LatLng-3")
                    .allowMainThreadQueries()
                    .build();
        }
    }

    public static AppDatabase getDatabase() {
        return database;
    }

}
