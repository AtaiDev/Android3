package com.example.roomless5depeninjection.frameworks.local;

import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.source.FilmSource;
import com.example.roomless5depeninjection.frameworks.local.dao.FilmDao;

import java.util.List;

public class RoomFilmDataSource  implements FilmSource {


    private FilmDao filmDao;

    public RoomFilmDataSource(FilmDao filmDao){
        this.filmDao = filmDao;
    }

    @Override
    public List<Film> getFilms() {
        return filmDao.getFilms();
    }

    @Override
    public void addFilm(Film film) {
        filmDao.insertFilm(film);
    }
}
