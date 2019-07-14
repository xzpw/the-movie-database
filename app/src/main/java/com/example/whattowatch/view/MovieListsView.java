package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.whattowatch.model.MyMovieModel;

import java.util.List;
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MovieListsView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showProgres();

    @StateStrategyType(SkipStrategy.class)
    void hideProgres();

    void showMovies(List<MyMovieModel> movies);

    void showError();
}
