package com.suhaili.submisionjetpack.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity

@Dao
interface DAOApps {
    @Query("SELECT * FROM Movie_Table ORDER BY _id ASC")
    fun getAllMovieDBPaging(): DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddFavouriteMovie(value: MovieEntity)

    @Delete
    fun DeleteFavouriteMovie(value: MovieEntity)

    @Query("SELECT * FROM Movie_Table ORDER BY _id ASC")
    fun getAllMovieDB(): LiveData<List<MovieEntity>>


    @Query("SELECT * FROM TV_Table ORDER BY _id ASC")
    fun getAllTVDB(): LiveData<List<TVEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun AddFavouriteTV(value: TVEntity)

    @Delete
    fun DeleteFavouriteTV(value: TVEntity)

    @Query("SELECT * FROM TV_Table ORDER BY _id ASC")
    fun getAllTVDBPaging(): DataSource.Factory<Int, TVEntity>


}