package com.example.whattowatch.mdbApi;

import com.example.whattowatch.model.response.list.MoviesObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMovieAPI {

     String API_KEY = "6d7cf38fae41aabbd628fc135149f671";
     String BASE_PICTURE = "https://image.tmdb.org/t/p/w500/";
    // https://api.themoviedb.org/3/movie/popular?api_key=6d7cf38fae41aabbd628fc135149f671&language=ru&page=1

    @GET("{section}")
    Observable<MoviesObject> getMovies(@Path("section") String section,
                                       @Query("language") String language,
                                       @Query("page") int page,
                                       @Query("api_key") String key);

}
