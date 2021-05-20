package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.suhaili.submisionjetpack.data.Repository
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
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {


    private lateinit var mainModel: MovieViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var Repo: Repository


    @Mock
    private lateinit var observer: Observer<ArrayList<MovieGetDataModel>>

    @Before
    fun SetUp() {
        mainModel = MovieViewModel(Repo)
    }

    @Test
    fun getAllMovieList() {
        val dummyMovie = DataDummy.getAllMovies()
        val MovieData = MutableLiveData<ArrayList<MovieGetDataModel>>()
        MovieData.value = dummyMovie
        `when`(Repo.getMovieData()).thenReturn(MovieData)
        val MovieList = mainModel.getMovieShow().value
        verify(Repo).getMovieData()
        assertNotNull(MovieList)
        assertEquals(5, MovieList?.size)

        mainModel.getMovieShow().observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }


}