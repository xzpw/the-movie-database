package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;

public interface MovieListsView extends MvpView {

    void showProgres();

    void hideProgres();

    void showMovies();

    void showError();
}
