package com.example.whattowatch.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whattowatch.model.mymodel.MyDetailModel
import com.example.whattowatch.model.mymodel.MyMovieModel

@Database(entities = [MyMovieModel::class, MyDetailModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localMovieDAO(): MovieDao
}