package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteTVViewModelTest {

    private lateinit var mainModel: FavouriteTVViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var Repo: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<TVEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TVEntity>


    @Before
    fun setUp() {
        mainModel = FavouriteTVViewModel(Repo)
    }

    @Test
    fun getTVPagedlist() {
        val dummyTV = pagedList
        `when`(dummyTV.size).thenReturn(5)
        val LiveDummy = MutableLiveData<PagedList<TVEntity>>()
        LiveDummy.value = dummyTV

        `when`(Repo.getAllDBTVPaging()).thenReturn(LiveDummy)
        val TvDB = mainModel.getAllTVDBPaging().value
        verify(Repo).getAllDBTVPaging()
        assertNotNull(TvDB)
        assertEquals(5, TvDB?.size)

        mainModel.getAllTVDBPaging().observeForever(observer)
        verify(observer).onChanged(dummyTV)

    }
}