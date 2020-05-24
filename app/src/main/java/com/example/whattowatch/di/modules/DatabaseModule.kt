package com.example.whattowatch.di.modules

import android.content.Context
import androidx.room.Room
import com.example.whattowatch.database.AppDatabase
import com.example.whattowatch.database.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {
    @Provides
    @Singleton
    fun privideDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_database")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideLocalMovieDAO(appDatabase: AppDatabase): MovieDao {
        return appDatabase.localMovieDAO()
    }

}