package com.example.whattowatch.repository;

import android.util.Log;

import com.example.whattowatch.model.mappers.DetailMapper;
import com.example.whattowatch.model.mappers.MovieMapper;
import com.example.whattowatch.model.mappers.VideoMapper;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class MoviesRepo {

  private RemoteMovieSource mRemoteMovieSource;
  private LocalMovieSource mLocalMovieSource;
  private final int FIRST_PAGE = 1;
    @Inject
    public MoviesRepo(RemoteMovieSource remoteMovieSource, LocalMovieSource localMovieSource) {
        this.mRemoteMovieSource = remoteMovieSource;
        this.mLocalMovieSource = localMovieSource;
    }

    public Flowable <List<MyMovieModel>> getAllLocalMovies(String type){
        return mLocalMovieSource.getAllMoviesByType(type);
    }

    public Flowable <List<MyMovieModel>> getMoviesFirstPageByType(String type){
        return mRemoteMovieSource.getMoviesList(type,FIRST_PAGE)
                .map(data -> {
                    return MovieMapper.convertListToMyModel(data.getResults(),type);  // преващаем в наш тип
                })
                .flatMap(localData -> {
                    return mLocalMovieSource.deleteAllbyType(type)              //удаляем старые записи
                            .andThen(mLocalMovieSource.insertAll(localData)     //Записываем в бд
                            .andThen(Flowable.just(localData)));            // возвращаем результат
                }).onErrorResumeNext(mLocalMovieSource.getAllMoviesByType(type));  //в случае ошибки берем данные с БД
    }

    public Flowable<List<MyMovieModel>> getMoviesPageByType(String type, int page){

        return mRemoteMovieSource.getMoviesList(type, page)
                .map(data ->{
                    return MovieMapper.convertListToMyModel(data.getResults(),type);   //загружаем остальные страници без сохранения
                });
    }

    public Flowable<MyDetailModel>getDetailMovieInfo(Integer id){
        return  mRemoteMovieSource.getMovieDetail(id)
                .map(data ->{
                 return  DetailMapper.convertToMyDetailModel(data);
                });
    }

    public Flowable<List<MyVideoModel>>getMovieTrailers(Integer id){
        return mRemoteMovieSource.getMovieVideos(id)
                .map(data ->{
                    return VideoMapper.convertToMyVideoList(data.getResults());
                });
    }

    public Flowable<List<MyMovieModel>> searchMovie(String query, int page){
        return mRemoteMovieSource.searchMovie(query, page)
                .map(data -> {
                    return MovieMapper.convertListToMyModel(data.getResults(),null);
                });
    }

    public Flowable<List<MyDetailModel>>getAllFAvorites(){
        return mLocalMovieSource.getAllFavorites();
    }

    public Completable add2Favorites(MyDetailModel detailModel){
        Log.d("mylog",detailModel.getName());
        return mLocalMovieSource.insertMovie2Favorites(detailModel);
    }
}
