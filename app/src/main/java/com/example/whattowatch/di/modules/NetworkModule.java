package com.example.whattowatch.di.modules;

import com.example.whattowatch.api.IMovieAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public OkHttpClient okHttpClient(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient okHttpClient){

       return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public IMovieAPI movieAPI(Retrofit retrofit){
        return retrofit.create(IMovieAPI.class);
    }

}
