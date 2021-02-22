package com.example.googlemap.data.local.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static final String POLYGON_KEY = "poligonkey";

        private static SharedPreferences sharedPreferences;

        private Prefs() {}
//
//        public static Prefs getPrefs(Context context){
//            if (sharedPreferences != null){
//                sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
//            }
//                return new Prefs();
//
//        }




    public static void init(Context context){
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }



    public static void putPolygon(){
        sharedPreferences.edit().putBoolean(POLYGON_KEY,true).apply();
    }

    public static void putPolyline(){
        sharedPreferences.edit().putBoolean(POLYGON_KEY,false).apply();
    }

    public static boolean  getPolygonState(){
        return sharedPreferences.getBoolean(POLYGON_KEY,false);
    }

}
