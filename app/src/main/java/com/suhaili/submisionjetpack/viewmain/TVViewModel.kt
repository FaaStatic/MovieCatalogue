package com.suhaili.submisionjetpack.viewmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.model.GetTVDataModel

class TVViewModel(private val repo: Repository) : ViewModel() {

    fun getTvShow(): LiveData<ArrayList<GetTVDataModel>>? {
        repo.getAllTV()
        return repo.getTVData()
    }

}