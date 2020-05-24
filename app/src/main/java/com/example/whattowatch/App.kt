package com.example.whattowatch

import android.app.Application
import com.example.whattowatch.di.AppComponent
import com.example.whattowatch.di.DaggerAppComponent
import com.example.whattowatch.di.modules.DatabaseModule
import com.example.whattowatch.di.modules.NetworkModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
                .networkModule(NetworkModule())
                .databaseModule(DatabaseModule(applicationContext))
                .build()
    }



    companion object {
        @JvmStatic
        var instance: App? = null
            private set

        var appComponent: AppComponent? = null
            private set
    }
}