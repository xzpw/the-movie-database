package com.example.whattowatch.repository;

import com.example.whattowatch.api.IMovieAPI;
import com.example.whattowatch.model.response.detail.DetailMovie;
import com.example.whattowatch.model.response.list.MoviesObject;
import com.example.whattowatch.model.response.video.MovieVideosResponse;
import com.example.whattowatch.ui.presenter.MovieListPresenter;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class RemoteMovieSource {

    private final String language = "ru";
    private final String region = "UA";
    public static final String API_KEY = "6d7cf38fae41aabbd628fc135149f671";
    public static final String VIDEO_YOUTUBE_THUMBNAIL = "https://img.youtube.com/vi/%s/sddefault.jpg";
    IMovieAPI api;

    @Inject
    public RemoteMovieSource(IMovieAPI api) {
        this.api = api;
    }

    public Flowable<MoviesObject> getMoviesList(String selection, int page) {  //возвращаем список фильмов по категории
        if(selection == MovieListPresenter.Type.UPCOMING.getSection()){
            return api.getUpcommingMovies(selection,language,page,region);
        }
        return api.getMovies(selection, language, page);
    }

    public  Flowable<DetailMovie> getMovieDetail(int id){
        return api.getDetail(id,language);
    }

    public Flowable<MovieVideosResponse>getMovieVideos(Integer id){
        return api.getMovieVideos(id);
    }

    public Flowable<MoviesObject> searchMovie(String query, int page){
        return api.searchMovie(language,page,query);
    }


}
