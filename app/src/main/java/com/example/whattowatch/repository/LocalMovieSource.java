package com.example.whattowatch.repository;

import android.util.Log;

import com.example.whattowatch.database.MovieDao;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalMovieSource {

    private MovieDao dao;

    @Inject
    public LocalMovieSource(MovieDao dao) {
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

    public Completable insertMovie2Favorites(MyDetailModel detailModel){
        return Completable.fromAction(()->{
            dao.insertDetail(detailModel);
        });
    }

    public Completable deleteFromFavorites(MyDetailModel detailModel){
        return Completable.fromAction(()->{
            dao.deleteFavorite(detailModel);
        });
    }

    public Flowable<List<MyDetailModel>> getAllFavorites(){
        return dao.getAllFavorites();
    }
}
