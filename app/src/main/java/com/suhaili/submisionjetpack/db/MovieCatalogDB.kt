package com.suhaili.submisionjetpack.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity

@Database(entities = [MovieEntity::class, TVEntity::class], version = 1)
abstract class MovieCatalogDB : RoomDatabase() {
    abstract fun MovieDAO(): DAOApps

    companion object {
        @Volatile
        private var Instance: MovieCatalogDB? = null
        fun CreateDB(ctx: Context): MovieCatalogDB =
            Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    ctx.applicationContext,
                    MovieCatalogDB::class.java,
                    "MovieCatalogDB"
                ).build().apply {
                    Instance = this
                }
            }
    }

}