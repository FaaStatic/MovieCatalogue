package com.suhaili.submisionjetpack.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.db.DAOApps

class LocalDataSource private constructor(private val MovieDAO: DAOApps) {

    companion object {
        private var Instance: LocalDataSource? = null

        fun getInstance(MovieDAO: DAOApps): LocalDataSource =
            Instance ?: LocalDataSource(MovieDAO).apply {
                Instance = this
            }
    }

    fun getAllTVList(): LiveData<List<TVEntity>> = MovieDAO.getAllTVDB()
    fun getAllMovieList(): LiveData<List<MovieEntity>> = MovieDAO.getAllMovieDB()
    fun insertMovie(value: MovieEntity) = MovieDAO.AddFavouriteMovie(value)
    fun deleteMovie(value: MovieEntity) = MovieDAO.DeleteFavouriteMovie(value)
    fun insertTV(value: TVEntity) = MovieDAO.AddFavouriteTV(value)
    fun deleteTV(value: TVEntity) = MovieDAO.DeleteFavouriteTV(value)
    fun getAllTVPaging(): DataSource.Factory<Int, TVEntity> = MovieDAO.getAllTVDBPaging()
    fun getAllMoviePaging(): DataSource.Factory<Int, MovieEntity> = MovieDAO.getAllMovieDBPaging()
}