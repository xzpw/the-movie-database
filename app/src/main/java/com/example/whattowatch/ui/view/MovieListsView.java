package com.example.whattowatch.ui.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.List;
@StateStrategyType(AddToEndSingleStrategy.class)
public interface MovieListsView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showProgres(boolean isVisible);

    void showMovies(List<MyMovieModel> movies);

    void showError();

    @StateStrategyType(SkipStrategy.class)
    void onRefreshed();

    void onLoaded(List<MyMovieModel> data);
}
