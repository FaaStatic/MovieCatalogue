package com.suhaili.submisionjetpack.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVDetailServiceModel(

    @field:SerializedName("first_air_date")
    var firstAirDate: String? = null,

    @field:SerializedName("overview")
    var overview: String? = null,


    @field:SerializedName("number_of_episodes")
    var numberOfEpisodes: Int? = null,

    @field:SerializedName("networks")
    var networks: ArrayList<NetworkModel>? = null,

    @field:SerializedName("poster_path")
    var posterPath: String? = null,


    @field:SerializedName("production_companies")
    var productionCompanies: ArrayList<CompanyModel>? = null,

    @field:SerializedName("genres")
    var genres: ArrayList<GenresItems>? = null,

    @field:SerializedName("original_name")
    var originalName: String? = null,

    @field:SerializedName("popularity")
    var popularity: Double? = null,

    @field:SerializedName("vote_average")
    var voteAverage: Double? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("tagline")
    var tagline: String? = null,

    @field:SerializedName("id")
    var id: Int? = null,

    @field:SerializedName("number_of_seasons")
    var numberOfSeasons: Int? = null,


    @field:SerializedName("last_air_date")
    var lastAirDate: String? = null,

    @field:SerializedName("homepage")
    var homepage: String? = null,

    @field:SerializedName("status")
    var status: String? = null

) : Parcelable


@Parcelize
data class GetTVDataModel(
    var id: Int? = null,
    var name: String? = null,
    var tagline: String? = null,
    var firstAirDate: String? = null,
    var lastAirDate: String? = null,
    var overview: String? = null,
    var numberOfEpisodes: String? = null,
    var numberOfSeasons: String? = null,
    var networks: ArrayList<NetworkModel>? = null,
    var posterPath: String? = null,
    var productionCompanies: ArrayList<CompanyModel>? = null,
    var genres: ArrayList<GenresItems>? = null,
    var originalName: String? = null,
    var popularity: String? = null,
    var voteAverage: String? = null,
    var homepage: String? = null,
    var status: String? = null
) : Parcelable


