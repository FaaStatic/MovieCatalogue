package com.suhaili.submisionjetpack.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnAirTVModel(

    @field:SerializedName("results")
    var results: ArrayList<ResultsTVItem>
) : Parcelable

@Parcelize
data class ResultsTVItem(

    @field:SerializedName("id")
    var id: Int? = null,

    ) : Parcelable
