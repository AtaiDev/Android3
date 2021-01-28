package com.example.retrodelsingleactivapplication.remote;

import com.example.retrodelsingleactivapplication.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitFactory {

    private static AndroidApi instance;

    private RetrofitFactory(){}

    public static AndroidApi getInstance(){
        if (instance == null) {
            instance = createInstance();
        }
        return  instance;
    }

    private static AndroidApi createInstance() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
                .create(AndroidApi.class);
    }


}
