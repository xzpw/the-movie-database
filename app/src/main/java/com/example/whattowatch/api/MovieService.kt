package com.example.whattowatch.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MovieService {
    var api: IMovieAPI? = null
        get() = if (field != null) {
            field
        } else {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            field = Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(IMovieAPI::class.java)
            field
        }
        private set
    const val API_KEY = "6d7cf38fae41aabbd628fc135149f671"
}