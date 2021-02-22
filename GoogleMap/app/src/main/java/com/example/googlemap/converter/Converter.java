package com.example.googlemap.converter;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converter {

    @TypeConverter
    public static String toString(LatLng position){
        Type type = new TypeToken<LatLng>(){}.getType();
        return new Gson().toJson(position,type);
    }


    @TypeConverter
    public static LatLng toModel(String json){
        Type type = new TypeToken<LatLng>(){}.getType();
        return  new Gson().fromJson(json,type);
    }

}
