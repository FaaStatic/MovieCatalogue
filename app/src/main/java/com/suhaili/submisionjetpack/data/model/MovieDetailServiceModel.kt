package com.suhaili.submisionjetpack.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailServiceModel(

    @field:SerializedName("overview")
    var overview: String? = null,

    @field:SerializedName("title")
    var originalTitle: String? = null,

    @field:SerializedName("poster_path")
    var posterPath: String? = null,

    @field:SerializedName("production_companies")
    var productionCompanies: ArrayList<CompanyModel>? = null,

    @field:SerializedName("release_date")
    var releaseDate: String? = null,

    @field:SerializedName("genres")
    var genres: ArrayList<GenresItems>? = null,

    @field:SerializedName("popularity")
    var popularity: Double? = null,

    @field:SerializedName("vote_average")
    var voteAverage: Double? = null,

    @field:SerializedName("tagline")
    var tagline: String? = null,

    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("homepage")
    var homepage: String? = null,

    @field:SerializedName("status")
    var status: String? = null
) : Parcelable

@Parcelize
data class MovieGetDataModel(
    var id: String? = null,
    var tagline: String? = null,
    var homepage: String? = null,
    var overview: String? = null,
    var originalTitle: String? = null,
    var posterPath: String? = null,
    var productionCompanies: ArrayList<CompanyModel>? = null,
    var status: String? = null,
    var voteAverage: Double? = null,
    var popularity: Double? = null,
    var genres: ArrayList<GenresItems>? = null,
    var releaseDate: String? = null,
) : Parcelable

