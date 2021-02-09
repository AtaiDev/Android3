package com.example.roomless5depeninjection.domain.repo;

import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.source.FilmSource;

import java.util.List;

public class FilmRepository {

    private FilmSource source;

    public FilmRepository(FilmSource source){
        this.source = source;
    }

    public List<Film> getFilms(){
        return source.getFilms();
    }

    public void addFilm(Film film){
        source.addFilm(film);
    }


}
