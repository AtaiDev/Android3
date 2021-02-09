package com.example.roomless5depeninjection.frameworks.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomless5depeninjection.frameworks.local.temp.FilmProducer;
import com.example.roomless5depeninjection.domain.models.Producer;

import java.util.List;

@Dao
public interface ProducerDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
     long insertProducer(Producer producer);

    @Query("Select * From producers")
    List<Producer> getProducers();

    @Query("Select * From producers")
    List<FilmProducer> getFilmProducer();
}
