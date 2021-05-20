package com.suhaili.submisionjetpack.viewmain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.suhaili.submisionjetpack.data.Repository
import com.suhaili.submisionjetpack.data.entitiy.MovieEntity
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
class FavouriteMovieViewModelTest {

    private lateinit var mainView: FavouriteMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var Repo: Repository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        mainView = FavouriteMovieViewModel(Repo)
    }

    @Test
    fun getAllMovieDB() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(1)
        val movie = MutableLiveData<PagedList<MovieEntity>>()
        movie.value = dummyMovie

        `when`(Repo.getAllDBMoviePaging()).thenReturn(movie)
        val movieList = mainView.getAllMovieDB().value
        verify(Repo).getAllDBMoviePaging()
        assertNotNull(movieList)
        assertEquals(1, movieList?.size)

        mainView.getAllMovieDB().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }


}