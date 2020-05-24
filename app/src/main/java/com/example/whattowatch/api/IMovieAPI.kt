package com.example.whattowatch.api

import com.example.whattowatch.model.response.cradits.MovieCredits
import com.example.whattowatch.model.response.detail.DetailMovie
import com.example.whattowatch.model.response.list.MoviesObject
import com.example.whattowatch.model.response.person.Person
import com.example.whattowatch.model.response.video.MovieVideosResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IMovieAPI {
    @GET("movie/{section}")
    fun getMovies(@Path("section") section: String?,
                  @Query("language") language: String?,
                  @Query("page") page: Int): Flowable<MoviesObject>

    @GET("movie/{section}")
    fun  //для отображения Upcoming нужно указать регион
            getUpcommingMovies(@Path("section") section: String?,
                               @Query("language") language: String?,
                               @Query("page") page: Int,
                               @Query("region") region: String?): Flowable<MoviesObject>

    @GET("movie/{movie_id}")
    fun getDetail(@Path("movie_id") id: Int,
                  @Query("language") language: String?): Flowable<DetailMovie>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int?): Flowable<MovieVideosResponse>

    @GET("search/movie")
    fun searchMovie(@Query("language") language: String?,
                    @Query("page") page: Int,
                    @Query("query") query: String?): Flowable<MoviesObject>

    @GET("movie/{id}/credits")
    fun getMovieCredits(@Path("id") id: Int?): Flowable<MovieCredits>

    @GET("person/{id}")
    fun getPersonDetail(@Path("id") id: Int,
                        @Query("language") language: String?): Flowable<Person>

    companion object {
        const val BASE_PICTURE = "https://image.tmdb.org/t/p/w500/"
    }
}