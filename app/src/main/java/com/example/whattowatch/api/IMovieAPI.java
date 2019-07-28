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
    // https://api.themoviedb.org/3/movie/popular?api_key=6d7cf38fae41aabbd628fc135149f671&language=ru&page=1

    @GET("{section}")
    Flowable<MoviesObject> getMovies(@Path("section") String section,
                                     @Query("language") String language,
                                     @Query("page") int page,
                                     @Query("api_key") String key);

    @GET("{movie_id}")
    Flowable<DetailMovie> getDetail(@Path("movie_id") int id,
                                      @Query("api_key") String key,
                                      @Query("language") String language);


//    @GET("movie/{id}/reviews")
//    Observable<MovieReviewsResponse> getMovieReviews(@Path("id") String movieId);
//
    @GET("{id}/videos")
    Flowable<MovieVideosResponse> getMovieVideos(@Path("id") Integer id,
                                                 @Query("api_key") String key);

}
