package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
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
class TVShowViewModelTest {
    private var id = "88396"

    private lateinit var mainView: TVShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repo: Repository

    @Mock
    private lateinit var observer: Observer<GetTVDataModel>

    @Mock
    private lateinit var observerDB: Observer<List<TVEntity>>


    @Before
    fun SetUp() {
        mainView = TVShowViewModel(Repo)
    }

    @Test
    fun getDetailTVTes() {
        val dataDummy = DataDummy.getTV(id)
        val DataDummyLive = MutableLiveData<GetTVDataModel>()
        DataDummyLive.value = dataDummy
        `when`(Repo.getTVDetailData()).thenReturn(DataDummyLive)
        val TVDetail = mainView.getDetailTV(id).value
        verify(Repo).getTVDetailData()
        assertNotNull(TVDetail)
        assertEquals(dataDummy.voteAverage, TVDetail?.voteAverage)
        assertEquals(dataDummy.id, TVDetail?.id)
        assertEquals(dataDummy.homepage, TVDetail?.homepage)
        assertEquals(dataDummy.posterPath, TVDetail?.posterPath)
        assertEquals(dataDummy.status, TVDetail?.status)
        assertEquals(dataDummy.originalName, TVDetail?.originalName)
        assertEquals(dataDummy.overview, TVDetail?.overview)
        assertEquals(dataDummy.tagline, TVDetail?.tagline)
        assertEquals(dataDummy.firstAirDate, TVDetail?.firstAirDate)
        assertEquals(dataDummy.lastAirDate, TVDetail?.lastAirDate)
        assertEquals(dataDummy.popularity, TVDetail?.popularity)
        assertEquals(dataDummy.numberOfEpisodes, TVDetail?.numberOfEpisodes)
        assertEquals(dataDummy.numberOfSeasons, TVDetail?.numberOfSeasons)


        mainView.getDetailTV(id).observeForever(observer)
        verify(observer).onChanged(dataDummy)


    }

    @Test
    fun testGetAllDB() {
        val Data_Dummy = DataDummy.getAllTVDB()
        val livedata = MutableLiveData<List<TVEntity>>()
        livedata.value = Data_Dummy
        `when`(Repo.getAllDBTV()).thenReturn(livedata)
        val DBList = mainView.getAllDbTV().value
        verify(Repo).getAllDBTV()
        assertNotNull(DBList)
        assertEquals(1, DBList?.size)

        mainView.getAllDbTV().observeForever(observerDB)
        verify(observerDB).onChanged(Data_Dummy)
    }


}