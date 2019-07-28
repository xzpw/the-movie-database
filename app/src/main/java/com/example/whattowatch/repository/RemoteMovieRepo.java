package com.example.whattowatch.repository;

import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.model.response.detail.DetailMovie;
import com.example.whattowatch.model.response.list.MoviesObject;
import com.example.whattowatch.model.response.video.MovieVideosResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RemoteMovieRepo {

    private final String language = "ru";
    public static final String API_KEY = "6d7cf38fae41aabbd628fc135149f671";
    public static final String VIDEO_YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/sddefault.jpg";
    IMovieAPI api;

    @Inject
    public RemoteMovieRepo(IMovieAPI api) {
        this.api = api;
    }

    public Flowable<MoviesObject> getMoviesList(String selection, int page) {  //возвращаем список фильмов по категории
        return api.getMovies(selection, language, page, API_KEY);
    }

    public  Flowable<DetailMovie> getMovieDetail(int id){
        return api.getDetail(id,API_KEY,language);
    }

    public Flowable<MovieVideosResponse>getMovieVideos(Integer id){
        return api.getMovieVideos(id, API_KEY);
    }


}
