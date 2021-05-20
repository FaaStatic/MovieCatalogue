package com.suhaili.submisionjetpack.viewmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.data.model.GetTVDataModel
import com.suhaili.submisionjetpack.util.Event

class TVShowViewModel(private val repo: Repository) : ViewModel() {

    fun getDetailTV(id: String): LiveData<GetTVDataModel> {
        repo.getTVDetail(id)
        return repo.getTVDetailData()
    }

    fun getLoad(): LiveData<Event<Boolean>> {
        return repo.getIsLoad()
    }

    fun addFavourite(value: TVEntity) {
        repo.insertTV(value)
    }

    fun delFavourite(value: TVEntity) {
        repo.deleteTV(value)
    }

    fun getAllDbTV(): LiveData<List<TVEntity>> {
        return repo.getAllDBTV()
    }

}