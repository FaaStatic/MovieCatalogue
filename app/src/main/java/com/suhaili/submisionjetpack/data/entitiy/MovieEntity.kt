package com.suhaili.submisionjetpack.data.entitiy

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Movie_Table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Int = 0,

    @NonNull
    @ColumnInfo(name = "idMovie")
    var idMovie: String? = null,

    @NonNull
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String? = null,

    @ColumnInfo(name = "release")
    var release: String? = null,


    @ColumnInfo(name = "rating")
    var rating: String? = null,

    @ColumnInfo(name = "posterpath")
    var posterPath: String? = null
) : Parcelable