package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.whattowatch.navigation.Router;


public interface MainView extends MvpView, Router {

    @StateStrategyType(SkipStrategy.class)
    void shwoPagerView();

    void showDetailView(int idMovie); // будет принимать итем для детализации

    void showFavorits();

}
