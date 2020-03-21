package com.example.whattowatch.di.modules;

import com.example.whattowatch.api.IMovieAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    public static final String API_KEY = "6d7cf38fae41aabbd628fc135149f671";
    @Provides
    @Singleton
    public OkHttpClient okHttpClient(){
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    HttpUrl httpUrl = request.url();
                    //Добавили APIKEY в параметр запроса интерцептора
                    HttpUrl newHttpUrl = httpUrl.newBuilder()
                            .addQueryParameter("api_key",API_KEY).build();

                    Request.Builder requestBuilder = request.newBuilder().url(newHttpUrl);
                    request = requestBuilder.build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Provides
    @Singleton
    public Retrofit retrofit(OkHttpClient okHttpClient){

       return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
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
