package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyMovieModel;

import java.util.List;

public interface DetailMovieView extends MvpView {

    void showProgres();

    void hideProgres();

    void showMovie(MyDetailModel movie);

    void showError();


}
