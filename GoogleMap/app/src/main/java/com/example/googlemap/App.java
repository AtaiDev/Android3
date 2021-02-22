package com.example.googlemap;

import android.app.Application;

import com.example.googlemap.data.local.sharedpref.Prefs;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Prefs.init(this);

    }
}
