package com.suhaili.submisionjetpack.di

import android.content.Context
import com.suhaili.submisionjetpack.data.LocalDataSource
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.db.MovieCatalogDB
import com.suhaili.submisionjetpack.util.AppExecutors

object InjectionFile {
    fun injectRepo(ctx: Context): Repository {
        val dbMovie = MovieCatalogDB.CreateDB(ctx)
        val localAccesDB = LocalDataSource.getInstance(dbMovie.MovieDAO())
        val appExecute = AppExecutors()
        return Repository.getInstance(ctx, localAccesDB, appExecute)
    }
}