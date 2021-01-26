package com.example.retrofilmproj.data.remote;

import com.example.retrofilmproj.data.model.Film;
import com.example.retrofilmproj.data.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GhibliApi {

    @GET(EndPoints.GET_FILM)
    Call<List<Film>> getFilms();

    @GET(EndPoints.GET_FILM_BY_ID)
    Call<Film> getFilmById(@Path("filmId") String id);

    @GET(EndPoints.GET_PEOPLE)
    Call<Person> getPeople(@Path("filmId") String id);

}
