package com.example.whattowatch.di;

import com.example.whattowatch.MainActivity;
import com.example.whattowatch.adaptor.ListAdaptor;
import com.example.whattowatch.di.modules.MainPresenterModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {MainPresenterModule.class})

public interface AppComponent {

    void inject(ListAdaptor listAdaptor);
    void inject(MainActivity mainActivity);
}
