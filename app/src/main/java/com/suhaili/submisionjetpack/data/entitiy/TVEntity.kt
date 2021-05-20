package com.suhaili.submisionjetpack.data.entitiy

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "TV_Table")
data class TVEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var _id: Int = 0,

    @NonNull
    @ColumnInfo(name = "idTV")
    var idTV: String? = null,

    @NonNull
    @ColumnInfo(name = "OriginalTitle")
    var originalTitle: String? = null,

    @ColumnInfo(name = "Episode")
    var episode: String? = null,

    @ColumnInfo(name = "Season")
    var season: String? = null,

    @ColumnInfo(name = "Release")
    var firsAir: String? = null,

    @ColumnInfo(name = "endRelease")
    var endAir: String? = null,

    @ColumnInfo(name = "Rating")
    var rating: String? = null,

    @ColumnInfo(name = "posterpath")
    var posterPath: String? = null
) : Parcelable