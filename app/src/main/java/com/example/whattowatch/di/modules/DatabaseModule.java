package com.example.whattowatch.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.example.whattowatch.database.AppDatabase;
import com.example.whattowatch.database.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    private Context context;

    public DatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public AppDatabase privideDatabase(){
        return Room.databaseBuilder(context,AppDatabase.class,"my_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    public MovieDao provideLocalMovieDAO(AppDatabase appDatabase){
        return appDatabase.localMovieDAO();
    }
}
