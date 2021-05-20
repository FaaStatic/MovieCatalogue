package com.suhaili.submisionjetpack.viewmain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.di.InjectionFile

class ViewModelFactory private constructor(private val Repo: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var Instance: ViewModelFactory? = null
        fun getInstance(ctx: Context): ViewModelFactory =
            Instance ?: synchronized(this) {
                Instance ?: ViewModelFactory(InjectionFile.injectRepo(ctx)).apply {
                    Instance = this
                }
            }
    }


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(Repo) as T
            }
            modelClass.isAssignableFrom(TVShowViewModel::class.java) -> {
                return TVShowViewModel(Repo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(Repo) as T
            }
            modelClass.isAssignableFrom(TVViewModel::class.java) -> {
                return TVViewModel(Repo) as T
            }
            modelClass.isAssignableFrom(FavouriteMovieViewModel::class.java) -> {
                return FavouriteMovieViewModel(Repo) as T
            }
            modelClass.isAssignableFrom(FavouriteTVViewModel::class.java) -> {
                return FavouriteTVViewModel(Repo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }


}