package com.example.roomless5depeninjection.frameworks.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import com.example.roomless5depeninjection.domain.models.Film;
import com.example.roomless5depeninjection.domain.models.Producer;

import com.example.roomless5depeninjection.frameworks.local.converters.MyConverter;
import com.example.roomless5depeninjection.frameworks.local.dao.FilmDao;
import com.example.roomless5depeninjection.frameworks.local.dao.ProducerDao;

@Database(entities = {Film.class, Producer.class}, version = 6)
@TypeConverters({MyConverter.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract FilmDao filmDao();

    public abstract ProducerDao producerDao();

}
