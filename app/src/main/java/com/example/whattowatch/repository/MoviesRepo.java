package com.example.whattowatch.repository;

import com.example.whattowatch.model.mappers.DetailMapper;
import com.example.whattowatch.model.mappers.MovieMapper;
import com.example.whattowatch.model.mappers.VideoMapper;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;

import java.util.List;
import java.util.Observable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class MoviesRepo {

  private  RemoteMovieRepo remoteMovieRepo;
  private  LocalMoviesRepo localMoviesRepo;

    @Inject
    public MoviesRepo(RemoteMovieRepo remoteMovieRepo, LocalMoviesRepo localMoviesRepo) {
        this.remoteMovieRepo = remoteMovieRepo;
        this.localMoviesRepo = localMoviesRepo;
    }

    public Flowable <List<MyMovieModel>> getAllLocalMovies(String type){
        return localMoviesRepo.getAllMoviesByType(type);
    }

    public Flowable <List<MyMovieModel>> getMoviesFirstPageByType(String type){
        return remoteMovieRepo.getMoviesList(type,1)
                .map(data -> {
                    return MovieMapper.convertListToMyModel(data.getResults(),type);  // преващаем в наш тип
                })
                .flatMap(localData -> {
                    return localMoviesRepo.insertAll(localData)     //Записываем в бд
                            .andThen(Flowable.just(localData));   // возвращаем результат
                }).onErrorResumeNext(localMoviesRepo.getAllMoviesByType(type));  //в случае ошибки берем данные с БД
    }

    public Flowable<List<MyMovieModel>> getMoviesPageByType(String type, int page){

        return remoteMovieRepo.getMoviesList(type,page)
                .map(data ->{
                    return MovieMapper.convertListToMyModel(data.getResults(),type);   //загружаем остальные страници без сохранения
                });
    }

    public Flowable<MyDetailModel>getDetailMovie(Integer id){
        return  remoteMovieRepo.getMovieDetail(id)
                .map(data ->{
                 return  DetailMapper.convertToMyDetailModel(data);
                })
                .flatMap(data -> {
                    //Дополняем модель детализации списком видео трейлеров
                    return remoteMovieRepo.getMovieVideos(id)
                            .map( videos -> {
                                List<MyVideoModel> myVideo = VideoMapper.convertToMyVideoList(videos.getResults());
                                data.setVideos(myVideo);
                                return data;
                            });
                    });
    }
}
