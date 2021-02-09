package com.example.roomless5depeninjection.domain.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "producers")
public class Producer {
    @PrimaryKey
    private int producerId;

    @ColumnInfo(name = "producer_name")
    private String producerName;

    @ColumnInfo(name = "film_id")
    private int filmId;

    public Producer(int producerId, String producerName, int filmId) {
        this.producerId = producerId;
        this.producerName = producerName;
        this.filmId = filmId;
    }

    public int getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    @Override
    public String toString() {
        return "Producer{" +
                "producerId=" + producerId +
                ", producerName='" + producerName + '\'' +
                ", filmId=" + filmId +
                '}';
    }
}
