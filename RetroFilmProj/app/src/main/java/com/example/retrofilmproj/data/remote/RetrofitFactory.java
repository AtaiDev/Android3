package com.example.retrofilmproj.data.remote;

import com.example.retrofilmproj.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static GhibliApi instance;

    public  RetrofitFactory() {}

    public static GhibliApi getInstance(){
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(GhibliApi.class);
        }
        return instance;
    }

}
