package com.example.weatherapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherapplication.R;
import com.example.weatherapplication.data.models.WeekModel;
import com.example.weatherapplication.data.models.adapter.WeatherAdapter;
import com.example.weatherapplication.data.models.com.WeatherHolder;
import com.example.weatherapplication.data.remote.RetroFactory;
import com.example.weatherapplication.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String KEY = "jajaja";
    private ActivityMainBinding binding;
    private List<WeekModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        callWeatherByCity();
        listAdder();
        binding.recyclerWeather.setAdapter(new WeatherAdapter(list));
    }


    private void callWeatherByCity() {
        RetroFactory
                .getInstance()
                .getWeather("Bishkek", "4bbf5a1ed98cd9f688ebb3651474cdd2", "metric")
                .enqueue(new Callback<WeatherHolder>() {
                    @Override
                    public void onResponse(Call<WeatherHolder> call, Response<WeatherHolder> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            setInViews(response);
                            Log.e(KEY, "onResponse: successful " + response.body().getMain().getFeelsLike());
                        } else {
                            Log.e(KEY, "onResponse: successful " + "this will work not successful " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<WeatherHolder> call, Throwable t) {
                        Log.e(KEY, "onResponse: failure " + t.getLocalizedMessage());
                    }
                });
    }
    private void setInViews(Response<WeatherHolder> response) {

        long am = Long.valueOf(response.body().getSys().getSunrise()) * 1000;
        Date sunrise = new java.util.Date(am);
        String sunRiseTime = new SimpleDateFormat("HH:mm").format(sunrise);

        long pm = Long.valueOf(response.body().getSys().getSunset()) * 1000;
        Date sunset = new java.util.Date(pm);
        String sunSetTime = new SimpleDateFormat("HH:mm").format(sunset);

        binding.tempIndicator.setText(String.format("%s", response.body().getMain().getTemp()));
        binding.sunnyTxt.setText(response.body().getWeather().get(0).getDescription());
        binding.humidityPercentage.setText(String.format("%s", response.body().getMain().getHumidity() + " %"));
        binding.mBarIndicator.setText(String.format("%s", response.body().getMain().getPressure().toString() + " mBar"));
        binding.windIndicator.setText(String.format("%s", response.body().getWind().getSpeed().toString() + " km/h"));
        binding.sunriseTiming.setText(String.format("%s", sunRiseTime + " AM"));
        binding.sunsetTiming.setText(String.format("%s", sunSetTime + " PM"));
        binding.upgradeIndicator.setText(String.format("%s", response.body().getMain().getTempMax().toString() + " \u00B0C"));
        binding.downgradeIndicator.setText(String.format("%s", response.body().getMain().getTempMin().toString() + " \u00B0C"));

    }

    private void listAdder() {
        list = new ArrayList<>();
        list.add(new WeekModel(R.drawable.ic_cloud,"Mon 21",24,35));
        list.add(new WeekModel(R.drawable.ic_humidity,"Tues 22",24,32));
        list.add(new WeekModel(R.drawable.ic_sun,"Wed 23",23,36));
        list.add(new WeekModel(R.drawable.ic_sunrise,"Thur 24",22,33));
        list.add(new WeekModel(R.drawable.ic_wind,"Fri 25",26,31));
    }
}