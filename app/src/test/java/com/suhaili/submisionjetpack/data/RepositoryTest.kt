package com.suhaili.submisionjetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
import com.suhaili.submisionjetpack.data.entitiy.TVEntity
import com.suhaili.submisionjetpack.data.model.GetTVDataModel
import com.suhaili.submisionjetpack.data.model.MovieGetDataModel
import com.suhaili.submisionjetpack.util.AppExecutors
import com.suhaili.submisionjetpack.util.DataDummy
import com.suhaili.submisionjetpack.util.Resource
import com.suhaili.submisionjetpack.utils.PagedTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class RepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val idMovie = "460465"
    private val idTV = 88396


    private val Local = mock(LocalDataSource::class.java)


    private val Execute = mock(AppExecutors::class.java)

    @Mock
    private val Repo = FakeRepository(Local, Execute)


    private val allMovie = DataDummy.getAllMovies()
    private val allTv = DataDummy.getAllTV()
    private val MovieDetail = DataDummy.getAllMovies()[0]
    private val TVDetail = DataDummy.getAllTV()[0]

    private val movieDummy = DataDummy.getAllMovieDB()
    private val tvDummy = DataDummy.getAllTVDB()


    @Test
    fun getAllMovie() {
        val data = MutableLiveData<ArrayList<MovieGetDataModel>>()
        data.value = allMovie
        `when`<MutableLiveData<ArrayList<MovieGetDataModel>>>(Repo.getMovieData()).thenReturn(data)
        val movieList = Repo.getMovieData().value
        verify(Repo).getMovieData()
        assertNotNull(movieList)
        assertEquals(data.value!!.size.toLong(), movieList!!.size.toLong())
    }

    @Test
    fun getAllTV() {
        val data = MutableLiveData<ArrayList<GetTVDataModel>>()
        data.value = allTv
        `when`<MutableLiveData<ArrayList<GetTVDataModel>>>(Repo.getTVData()).thenReturn(data)
        val movieList = Repo.getTVData().value
        verify(Repo).getTVData()
        assertNotNull(movieList)
        assertEquals(data.value!!.size.toLong(), movieList!!.size.toLong())
    }


    @Test
    fun getMovieDetail() {
        val data = MutableLiveData<MovieGetDataModel>()
        data.value = MovieDetail
        `when`<MutableLiveData<MovieGetDataModel>>(Repo.getMovieDetailData()).thenReturn(data)
        val movieData = Repo.getMovieDetailData().value
        verify(Repo).getMovieDetailData()
        assertNotNull(movieData)
        assertEquals(data.value!!.id, movieData!!.id)
    }

    @Test
    fun getTVDetail() {
        val data = MutableLiveData<GetTVDataModel>()
        data.value = TVDetail
        `when`<MutableLiveData<GetTVDataModel>>(Repo.getTVDetailData()).thenReturn(data)
        val TVData = Repo.getTVDetailData().value
        verify(Repo).getTVDetailData()
        assertNotNull(TVData)
        assertEquals(data.value!!.id, TVData!!.id)
    }

    @Test
    fun getAllMovieinDB() {
        val dummyData = MutableLiveData<List<MovieEntity>>()
        dummyData.value = DataDummy.getAllMovieDB()
        `when`(Local.getAllMovieList()).thenReturn(dummyData)
        `when`(Repo.getAllMovieDB()).thenReturn(dummyData)
        val movieList = Local.getAllMovieList().value
        val movieRepo = Repo.getAllMovieDB().value
        verify(Local).getAllMovieList()
        verify(Repo).getAllMovieDB()
        assertNotNull(movieList)
        assertNotNull(movieRepo)
        assertEquals(movieDummy.size.toLong(), movieList?.size?.toLong())
        assertEquals(movieDummy.size.toLong(), movieRepo?.size?.toLong())

    }

    @Test
    fun getAllTVinDB() {
        val dummyData = MutableLiveData<List<TVEntity>>()
        dummyData.value = DataDummy.getAllTVDB()
        `when`(Local.getAllTVList()).thenReturn(dummyData)
        `when`(Repo.getAllDBTV()).thenReturn(dummyData)
        val TVList = Local.getAllTVList().value
        val TVRepo = Repo.getAllDBTV().value
        verify(Local).getAllTVList()
        verify(Repo).getAllDBTV()
        assertNotNull(TVList)
        assertNotNull(TVRepo)
        assertEquals(tvDummy.size.toLong(), TVList?.size?.toLong())
        assertEquals(tvDummy.size.toLong(), TVRepo?.size?.toLong())
    }

    @Test
    fun CheckPagedListMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(Local.getAllMoviePaging()).thenReturn(dataSourceFactory)
        Repo.getAllDBMoviePaging()
        val pageMovie = Resource.success(PagedTestUtil.mockPagedList(DataDummy.getAllMovieDB()))
        verify(Repo, times(1)).getAllDBMoviePaging()
        assertNotNull(pageMovie.data)
        assertEquals(movieDummy.size.toLong(), pageMovie.data?.size?.toLong())
    }

    @Test
    fun CheckPagedListTV() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVEntity>
        `when`(Local.getAllTVPaging()).thenReturn(dataSourceFactory)
        Repo.getAllDBTVPaging()
        val pageTV = Resource.success(PagedTestUtil.mockPagedList(DataDummy.getAllTVDB()))
        verify(Repo, times(1)).getAllDBTVPaging()
        assertNotNull(pageTV.data)
        assertEquals(tvDummy.size.toLong(), pageTV.data?.size?.toLong())
    }


}