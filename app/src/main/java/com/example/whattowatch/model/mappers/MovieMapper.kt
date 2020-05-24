package com.example.whattowatch.model.mappers

import com.example.whattowatch.model.mymodel.MyMovieModel
import com.example.whattowatch.model.response.list.Movie
import java.util.*

object MovieMapper {
    fun convertToMyModel(movie: Movie, type: String?): MyMovieModel {
        return MyMovieModel(movie.title,
                movie.overview,
                movie.releaseDate,
                movie.voteAverage?.toFloat(),
                movie.posterPath,
                movie.id,
                type)
    }

    fun convertListToMyModel(movies: List<Movie>, type: String?): List<MyMovieModel> {
        val myMovies: MutableList<MyMovieModel> = ArrayList()
        for (m in movies) {
            myMovies.add(convertToMyModel(m, type))
        }
        return myMovies
    }
}