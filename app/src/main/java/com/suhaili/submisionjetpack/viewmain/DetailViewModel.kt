package com.suhaili.submisionjetpack.viewmain


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel
import com.suhaili.submisionjetpack.util.Event

class DetailViewModel(private val repo: Repository) : ViewModel() {

    private var isLoading = MutableLiveData<Event<Boolean>>()

    fun getDataMovies(id: String): LiveData<MovieGetDataModel> {
        repo.getDetailMovie(id)
        return repo.getMovieDetailData()
    }

    fun getLoading(): LiveData<Event<Boolean>> {
        isLoading = repo.getIsLoad()
        return isLoading
    }

    fun addFavourite(value: MovieEntity) {
        repo.insertMovie(value)
    }

    fun delFavourite(value: MovieEntity) {
        repo.deleteMovie(value)
    }

    fun getAllDbMovie(): LiveData<List<MovieEntity>> {
        return repo.getAllMovieDB()
    }


}

