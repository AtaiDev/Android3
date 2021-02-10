package com.example.roomless5depeninjection.domain.source;

import android.util.Log;

import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteSource {

    private static final String TAG = "remoteSource";
    private final List<Film> filmList = new ArrayList<>();

    public List<Film> getFilmsRemote(){
        RetrofitBuilder
                .getInstance()
                .getFilms()
                .enqueue(new Callback<List<Film>>() {
                    @Override
                    public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Log.e(TAG, "onResponse: success");
                            filmList.addAll(response.body());
                        } else {
                            Log.e(TAG, "onResponse: failure " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Film>> call, Throwable t) {
                        Log.e(TAG, "onFailure: failed to load");
                    }
                });
        return filmList;
    }
}
