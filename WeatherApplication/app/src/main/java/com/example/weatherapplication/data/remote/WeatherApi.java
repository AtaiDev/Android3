package com.example.weatherapplication.data.remote;


import com.example.weatherapplication.data.models.com.WeatherHolder;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET(EndPoints.GET_WEATHER)
    Call<WeatherHolder> getWeather(@Query("q") String cityName,
                                   @Query("appid") String appId,
                                   @Query("units") String units);

}
