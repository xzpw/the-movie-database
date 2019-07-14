package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


public interface MainView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void shwoPagerView();

    void showDetailView(); // будет принимать итем для детализации

    void showFavorits();

}
