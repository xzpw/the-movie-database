package com.example.whattowatch.model.mappers;

import com.example.whattowatch.model.mymodel.MyMovieModel;
import com.example.whattowatch.model.response.list.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static MyMovieModel convertToMyModel(Movie movie){

        return new MyMovieModel(movie.getTitle(),
                movie.getOverview(),
                movie.getReleaseDate(),
                movie.getVoteAverage().floatValue(),
                movie.getPosterPath(),
                movie.getId());
    }

    public static List<MyMovieModel> convertListToMyModel(List<Movie> movies){
        List<MyMovieModel> myMovies = new ArrayList<>();
        for(Movie m : movies){
            myMovies.add(convertToMyModel(m));
        }
        return myMovies;
    }
}
