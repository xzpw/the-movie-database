package com.example.whattowatch.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.whattowatch.view.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public MainPresenter() {
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().shwoPagerView(); // при создании отображает основной экрн
    }
}
