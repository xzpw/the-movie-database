package com.example.whattowatch.api;

import com.example.whattowatch.model.response.detail.DetailMovie;
import com.example.whattowatch.model.response.list.MoviesObject;
import com.example.whattowatch.model.response.video.MovieVideosResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMovieAPI {


     String BASE_PICTURE = "https://image.tmdb.org/t/p/w500/";

    @GET("movie/{section}")
    Flowable<MoviesObject> getMovies(@Path("section") String section,
                                     @Query("language") String language,
                                     @Query("page") int page);

    @GET("movie/{section}") //для отображения Upcoming нужно указать регион
    Flowable<MoviesObject> getUpcommingMovies(@Path("section") String section,
                                              @Query("language") String language,
                                              @Query("page") int page,
                                              @Query("region") String region);

    @GET("movie/{movie_id}")
    Flowable<DetailMovie> getDetail(@Path("movie_id") int id,
                                      @Query("language") String language);


//    @GET("movie/{id}/reviews")
//    Observable<MovieReviewsResponse> getMovieReviews(@Path("id") String movieId);
//
    @GET("movie/{id}/videos")
    Flowable<MovieVideosResponse> getMovieVideos(@Path("id") Integer id);

    @GET("search/movie")
    Flowable<MoviesObject> searchMovie(@Query("language") String language,
                                       @Query("page") int page,
                                       @Query("query") String query);


}
