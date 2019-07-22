package com.example.whattowatch.di.modules;

import com.example.whattowatch.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainPresenterModule {

    @Provides
    @Singleton
    MainPresenter createMainPresenter(){
        return new MainPresenter();
    }
}
