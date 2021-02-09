package com.example.roomless5depeninjection.network;

import com.example.roomless5depeninjection.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static FilmApi instance;

    private RetrofitBuilder() {
    }


    public static FilmApi getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static FilmApi createInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
                .create(FilmApi.class);
    }
}
