package com.example.roomless5depeninjection.domain.source;

import com.example.roomless5depeninjection.domain.models.Film;

import java.util.List;

public interface FilmSource {

    List<Film> getFilms();

    void addFilm(Film film);

}
