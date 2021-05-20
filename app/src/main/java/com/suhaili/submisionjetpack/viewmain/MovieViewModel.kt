package com.suhaili.submisionjetpack.viewmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel

class MovieViewModel(private val repo: Repository) : ViewModel() {

    fun getMovieShow(): LiveData<ArrayList<MovieGetDataModel>> {
        repo.getAllMovies()
        return repo.getMovieData()
    }
}