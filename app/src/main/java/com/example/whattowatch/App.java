package com.example.whattowatch;

import android.app.Application;

import com.example.whattowatch.di.AppComponent;
import com.example.whattowatch.di.DaggerAppComponent;
import com.example.whattowatch.di.modules.MainPresenterModule;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appComponent = DaggerAppComponent.builder()
                .mainPresenterModule(new MainPresenterModule())
                .build();
    }

    public  static App getInstance(){
        return instance;
    }

    private AppComponent appComponent;

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
