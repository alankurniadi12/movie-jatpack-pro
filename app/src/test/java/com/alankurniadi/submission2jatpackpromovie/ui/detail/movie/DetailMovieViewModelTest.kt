package com.alankurniadi.submission2jatpackpromovie.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val dummy = DataDummy.getMovieDetail()
    private val id = dummy.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<Detail.Movie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieDbRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<Detail.Movie>()
        movie.value = dummy

        `when`(movieDbRepository.getDetailMovie(id)).thenReturn(movie)
        val detail = viewModel.getDetailMovie(id).value
        verify(movieDbRepository).getDetailMovie(id)

        assertNotNull(detail)
        assertEquals(detail?.id, dummy.id)
        assertEquals(detail?.backdrop_path, dummy.backdrop_path)
        assertEquals(detail?.title, dummy.title)
        assertEquals(detail?.overview, dummy.overview)
        assertEquals(detail?.poster_path, dummy.poster_path)
        assertEquals(detail?.release_date, dummy.release_date)
        assertEquals(detail?.vote_average, dummy.vote_average)

        viewModel.getDetailMovie(id).observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}