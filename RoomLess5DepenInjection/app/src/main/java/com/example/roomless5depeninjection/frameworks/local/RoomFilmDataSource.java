package com.example.roomless5depeninjection.frameworks.local;
import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.source.FilmSource;
import com.example.roomless5depeninjection.frameworks.local.dao.FilmDao;

import java.util.List;

public class RoomFilmDataSource implements FilmSource {


    private final FilmDao filmDao;

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

    @Override
    public void addFilms(List<Film> listFilm) {
        filmDao.insertFilms(listFilm);
    }

    @Override
    public void deleteFilm(Film film) {
        this.filmDao.deleteFilm(film);
    }


}
