package com.example.whattowatch.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;

@Database(entities = { MyMovieModel.class, MyDetailModel.class},
        version = 2)

public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao localMovieDAO();
}
