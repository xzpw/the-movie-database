package com.example.whattowatch.view;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void shwoPagerView();

    void showDetailView(); // будет принимать итем для детализации

    void showFavorits();

}
