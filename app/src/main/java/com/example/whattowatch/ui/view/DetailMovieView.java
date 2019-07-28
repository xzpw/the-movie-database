package com.example.whattowatch.ui.view;

import com.arellomobile.mvp.MvpView;
import com.example.whattowatch.model.mymodel.MyDetailModel;

public interface DetailMovieView extends MvpView {

    void showProgres();

    void hideProgres();

    void showMovie(MyDetailModel movie);

    void showError();


}
