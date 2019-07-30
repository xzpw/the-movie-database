package com.example.whattowatch.repository;

import com.example.whattowatch.database.MovieDao;
import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalMoviesRepo {

    private MovieDao dao;

    @Inject
    public LocalMoviesRepo(MovieDao dao) {
        this.dao = dao;
    }

    public Completable insertAll(List<MyMovieModel> movieModels){
        return Completable.fromAction(() -> {
            dao.insertAllMovies(movieModels);
        });
    }

    public Flowable<List<MyMovieModel>> getAllMoviesByType(String type){
        return dao.getMoviesByType(type);
    }

    public Completable deleteAllbyType(String type){
        return Completable.fromAction(()->{
            dao.deleteAllbyType(type);
        });
    }
}
