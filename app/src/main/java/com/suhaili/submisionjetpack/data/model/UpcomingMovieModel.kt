package com.suhaili.submisionjetpack.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpcomingMovieModel(

    @field:SerializedName("results")
    var results: ArrayList<ResultsMoviesItem>
) : Parcelable

@Parcelize
data class ResultsMoviesItem(

    @field:SerializedName("id")
    var id: Int? = null,

    ) : Parcelable
