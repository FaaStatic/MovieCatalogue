package com.suhaili.submisionjetpack.api

import com.suhaili.submisionjetpack.BuildConfig
import com.suhaili.submisionjetpack.data.model.MovieDetailServiceModel
import com.suhaili.submisionjetpack.data.model.OnAirTVModel
import com.suhaili.submisionjetpack.data.model.TVDetailServiceModel
import com.suhaili.submisionjetpack.data.model.UpcomingMovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetroAPI {

    @GET("popular?api_key=${BuildConfig.KEY_API}")
    fun getAllMovie(): Call<UpcomingMovieModel>

    @GET("{id}?api_key=${BuildConfig.KEY_API}")
    fun getDetailMovies(
        @Path("id") id: String
    ): Call<MovieDetailServiceModel>

    @GET("popular?api_key=${BuildConfig.KEY_API}")
    fun getAllTV(): Call<OnAirTVModel>

    @GET("{id}?api_key=${BuildConfig.KEY_API}")
    fun getDetailTV(@Path("id") id: String): Call<TVDetailServiceModel>


}