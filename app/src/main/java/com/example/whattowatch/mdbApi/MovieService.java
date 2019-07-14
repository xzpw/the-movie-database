package com.example.whattowatch.mdbApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieService {
    private static IMovieAPI api;

    public static IMovieAPI getApi(){
        if(api != null){
            return api;
        }else {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                                .addInterceptor(interceptor)
                                .build();

            api = new Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IMovieAPI.class);
            return api;

        }
    }
}
