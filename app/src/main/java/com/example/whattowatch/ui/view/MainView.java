package com.example.whattowatch.ui.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.whattowatch.ui.Router;


public interface MainView extends MvpView, Router {

    @StateStrategyType(SkipStrategy.class)
    void showPagerView();

    void showDetailView(int idMovie); // будет принимать итем для детализации

    void showFavorites();

}
