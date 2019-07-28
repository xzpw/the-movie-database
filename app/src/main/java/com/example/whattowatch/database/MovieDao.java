package com.example.whattowatch.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllMovies(List<MyMovieModel> movies);

    @Update
    void updateAllMovies(List<MyMovieModel> movies);

    @Query("SELECT * FROM MyMovieModel WHERE type = :type")
    Flowable<List<MyMovieModel>> getMoviesByType (String type);



}
