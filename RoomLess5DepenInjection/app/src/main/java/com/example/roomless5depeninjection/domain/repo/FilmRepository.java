package com.example.roomless5depeninjection.domain.repo;

import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.source.FilmSource;
import com.example.roomless5depeninjection.domain.source.RemoteSource;

import java.util.List;

public class FilmRepository {

    private final FilmSource source;
    private final RemoteSource remoteSource;

    public FilmRepository(FilmSource source, RemoteSource remoteSource) {
        this.source = source;
        this.remoteSource = remoteSource;
    }

    public List<Film> getFilms() {
        return source.getFilms();
    }

    public List<Film> getFilmsRemote() {
        if (source.getFilms().isEmpty()) {
            source.addFilms(remoteSource.getFilmsRemote());
        }
        return source.getFilms();
    }

    public void addFilm(Film film) {
        source.addFilm(film);
    }

    public void deleteFilm(Film film){
        source.deleteFilm(film);
    }



}
