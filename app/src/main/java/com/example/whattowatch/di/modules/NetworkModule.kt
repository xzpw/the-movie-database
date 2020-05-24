package com.example.whattowatch.di.modules

import com.example.whattowatch.api.IMovieAPI
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain: Interceptor.Chain ->
                    var request = chain.request()
                    val httpUrl = request.url
                    //Добавили APIKEY в параметр запроса интерцептора
                    val newHttpUrl = httpUrl.newBuilder()
                            .addQueryParameter("api_key", API_KEY).build()
                    val requestBuilder = request.newBuilder().url(newHttpUrl)
                    request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun movieAPI(retrofit: Retrofit): IMovieAPI {
        return retrofit.create(IMovieAPI::class.java)
    }

    companion object {
        const val API_KEY = "6d7cf38fae41aabbd628fc135149f671"
    }
}