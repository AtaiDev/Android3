package com.example.roomless5depeninjection.frameworks.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomless5depeninjection.domain.models.Film;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFilm(Film film);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFilms(List<Film> list);

    @Delete
    void deleteFilm(Film film);

    @Update
    void updateFilm(Film film);

    @Query("Select * From film")
    List<Film> getFilms();





}
