package com.example.weatherapplication.data.remote;



import com.example.weatherapplication.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFactory {

    private static WeatherApi instance;

    private RetroFactory(){}

    public static WeatherApi getInstance(){
        if (instance == null){
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            instance = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClientBuilder.build())
                        .baseUrl(BuildConfig.BASE_URL)
                        .build()
                        .create(WeatherApi.class);
            }
            return  instance;
    }

}
