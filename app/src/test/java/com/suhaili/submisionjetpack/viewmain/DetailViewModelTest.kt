package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel
import com.suhaili.submisionjetpack.util.DataDummy
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
class DetailViewModelTest {
    private lateinit var mainView: DetailViewModel

    private val id = "460465"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repo: Repository

    @Mock
    private lateinit var observer: Observer<MovieGetDataModel>

    @Mock
    private lateinit var observerDB: Observer<List<MovieEntity>>


    @Before
    fun setUp() {
        mainView = DetailViewModel(Repo)
    }

    @Test
    fun GetDetailMovie() {
        val data = DataDummy.getMovie(id)
        val dataLive = MutableLiveData<MovieGetDataModel>()
        dataLive.value = data

        `when`(Repo.getMovieDetailData()).thenReturn(dataLive)
        val movieDetail = mainView.getDataMovies(id).value
        verify(Repo).getMovieDetailData()
        assertNotNull(movieDetail)
        assertEquals(data.voteAverage, movieDetail?.voteAverage)
        assertEquals(data.id, movieDetail?.id)
        assertEquals(data.homepage, movieDetail?.homepage)
        assertEquals(data.posterPath, movieDetail?.posterPath)
        assertEquals(data.status, movieDetail?.status)
        assertEquals(data.originalTitle, movieDetail?.originalTitle)
        assertEquals(data.overview, movieDetail?.overview)
        assertEquals(data.tagline, movieDetail?.tagline)
        assertEquals(data.releaseDate, movieDetail?.releaseDate)
        assertEquals(data.popularity, movieDetail?.popularity)

        mainView.getDataMovies(id).observeForever(observer)
        verify(observer).onChanged(data)
    }

    @Test
    fun testGetAllDB() {
        val Data_Dummy = DataDummy.getAllMovieDB()
        val livedata = MutableLiveData<List<MovieEntity>>()
        livedata.value = Data_Dummy
        `when`(Repo.getAllMovieDB()).thenReturn(livedata)
        val DBList = mainView.getAllDbMovie().value
        verify(Repo).getAllMovieDB()
        assertNotNull(DBList)
        assertEquals(1, DBList?.size)

        mainView.getAllDbMovie().observeForever(observerDB)
        verify(observerDB).onChanged(Data_Dummy)
    }


}