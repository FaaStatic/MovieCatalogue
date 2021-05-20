package com.suhaili.submisionjetpack.viewmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity

class FavouriteMovieViewModel(private val repo: Repository) : ViewModel() {
    fun getAllMovieDB(): LiveData<PagedList<MovieEntity>> {
        return repo.getAllDBMoviePaging()
    }
}