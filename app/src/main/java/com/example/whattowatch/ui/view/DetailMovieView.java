package com.example.whattowatch.ui.view;

import com.arellomobile.mvp.MvpView;
import com.example.whattowatch.model.mymodel.MyCastModel;
import com.example.whattowatch.model.mymodel.MyDetailModel;
import com.example.whattowatch.model.mymodel.MyVideoModel;

import java.util.List;

public interface DetailMovieView extends MvpView {

    void showProgress(boolean isVisible);

    void showMovieInfo(MyDetailModel movie);

    void showMovieTrailer(List<MyVideoModel> videoModel);

    void showMovieCast(List<MyCastModel> castModel);

    void showErrorCast();

    void showErrorInfo();

    void showErrorTrailer();


}
