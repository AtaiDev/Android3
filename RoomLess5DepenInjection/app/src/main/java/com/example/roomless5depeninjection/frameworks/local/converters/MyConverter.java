package com.example.roomless5depeninjection.frameworks.local.converters;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MyConverter {

    @TypeConverter
    public String toType(List<String> list) {
        return new Gson().toJson(list);
    }

    @TypeConverter
    public List<String> fromType(String list) {
        Type type = new TypeToken<List<String>>(){}.getType();
        return new Gson().fromJson(list, type);
    }



}
