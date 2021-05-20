package com.suhaili.submisionjetpack.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIConfig {

    companion object {
        fun GetMovieService(): RetroAPI {
            val logging = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder()
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build()
            val retro = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retro.create(RetroAPI::class.java)
        }

        fun GetTVService(): RetroAPI {
            val logging = HttpLoggingInterceptor()
            val client = OkHttpClient.Builder()
                .addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build()
            val retro = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/tv/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retro.create(RetroAPI::class.java)
        }

    }
}