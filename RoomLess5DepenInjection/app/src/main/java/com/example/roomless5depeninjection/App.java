package com.example.roomless5depeninjection;

import android.app.Application;

import androidx.room.Room;

import com.example.roomless5depeninjection.domain.repo.FilmRepository;
import com.example.roomless5depeninjection.domain.source.FilmSource;
import com.example.roomless5depeninjection.domain.source.RemoteSource;
import com.example.roomless5depeninjection.frameworks.local.AppDataBase;
import com.example.roomless5depeninjection.frameworks.local.RoomFilmDataSource;
import com.example.roomless5depeninjection.frameworks.local.dao.FilmDao;


public class App extends Application {

    public static FilmRepository filmRepo;

    @Override
    public void onCreate() {
        super.onCreate();
        FilmDao filmDao = Room.databaseBuilder(
                getApplicationContext(),
                AppDataBase.class,
                "Android-3")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().filmDao();

        FilmSource filmSource = new RoomFilmDataSource(filmDao);
        filmRepo = new FilmRepository(filmSource,new RemoteSource());
    }

}
