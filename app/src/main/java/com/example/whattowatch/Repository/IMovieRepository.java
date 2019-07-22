package com.example.whattowatch.Repository;


public interface IMovieRepository {

    void loadMovieList(String selection, int page);
    void loadDetailMovie(int id);
}
