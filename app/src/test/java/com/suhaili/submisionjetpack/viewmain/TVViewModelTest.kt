package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.model.GetTVDataModel
import com.suhaili.submisionjetpack.util.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TVViewModelTest {

    private lateinit var mainView: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repo: Repository

    @Mock
    private lateinit var observer: Observer<ArrayList<GetTVDataModel>>


    @Before
    fun setUp() {
        mainView = TVViewModel(Repo)
    }

    @Test
    fun getAllTVTes() {
        val dummyTV = DataDummy.getAllTV()
        val DataLive = MutableLiveData<ArrayList<GetTVDataModel>>()
        DataLive.value = dummyTV
        `when`(Repo.getTVData()).thenReturn(DataLive)
        val Tvlist = mainView.getTvShow()?.value
        verify(Repo).getTVData()
        assertNotNull(Tvlist)
        assertEquals(1, Tvlist?.size)

        mainView.getTvShow()?.observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }


}