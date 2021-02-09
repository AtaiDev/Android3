package com.example.roomless5depeninjection.frameworks.local.temp;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.models.Producer;

import java.util.List;

public class FilmProducer {
    @Embedded
    public Producer producer;

    @Relation(entityColumn = "id", parentColumn = "film_id")
    public List<Film> getFilms;


    @Override
    public String toString() {
        return "FilmProducer{" +
                "producer=" + producer +
                ", getFilms=" + getFilms +
                '}';
    }
}
