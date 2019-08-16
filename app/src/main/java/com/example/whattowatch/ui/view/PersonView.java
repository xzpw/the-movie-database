package com.example.whattowatch.ui.view;

import com.arellomobile.mvp.MvpView;
import com.example.whattowatch.model.mymodel.MyPersonModel;

public interface PersonView extends MvpView {

    void showProgress(boolean isVisible);
    void showPersonInfo(MyPersonModel personModel);
    void showError();
}
