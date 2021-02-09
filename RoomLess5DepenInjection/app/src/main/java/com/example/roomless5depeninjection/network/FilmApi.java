package com.example.roomless5depeninjection.network;

import com.example.roomless5depeninjection.domain.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {

    @GET(EndPoints.END_POINT)
    Call<List<Film>> getFilms();

    @GET(EndPoints.END_POINT_BYID)
    Call<Film> getFilm(@Path("id") String filmId);

}
