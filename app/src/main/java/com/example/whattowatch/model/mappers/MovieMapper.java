package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.model.response.list.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static MyMovieModel convertToMyModel(Movie movie, String type){

        return new MyMovieModel(movie.getTitle(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getVoteAverage().floatValue(),
                movie.getPosterPath(),
                movie.getId(),
                type);
    }

    public static List<MyMovieModel> convertListToMyModel(List<Movie> movies, String type){
        List<MyMovieModel> myMovies = new ArrayList<>();
        for(Movie m : movies){
            myMovies.add(convertToMyModel(m, type));
        }
        return myMovies;
    }
}
