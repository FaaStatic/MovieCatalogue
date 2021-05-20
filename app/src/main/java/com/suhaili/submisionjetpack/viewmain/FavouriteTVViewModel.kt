package com.suhaili.submisionjetpack.viewmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.TVEntity

class FavouriteTVViewModel(private val Repo: Repository) : ViewModel() {
    fun getAllTVDBPaging(): LiveData<PagedList<TVEntity>> {
        return Repo.getAllDBTVPaging()
    }
}