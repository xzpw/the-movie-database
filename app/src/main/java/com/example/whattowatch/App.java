package com.example.whattowatch;

import android.app.Application;

import com.example.whattowatch.di.AppComponent;
import com.example.whattowatch.di.DaggerAppComponent;
import com.example.whattowatch.di.modules.DatabaseModule;
import com.example.whattowatch.di.modules.NetworkModule;

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        appComponent = DaggerAppComponent.builder()
        .networkModule(new NetworkModule())
        .databaseModule(new DatabaseModule(getApplicationContext()))
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
