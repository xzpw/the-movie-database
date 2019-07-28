package com.example.whattowatch.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whattowatch.model.mymodel.MyMovieModel;

@Database(entities = MyMovieModel.class, version = 1)

public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao localMovieDAO();
}
