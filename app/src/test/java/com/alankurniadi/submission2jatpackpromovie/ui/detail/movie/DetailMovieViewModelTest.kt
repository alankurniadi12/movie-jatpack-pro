package com.alankurniadi.submission2jatpackpromovie.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
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
    private val dummy = DataDummy.getNowPlayingMovie()[0]
    private val id = dummy.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<NowPlayingMovie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieDbRepository)
        viewModel.setDetailMovielID(id.toString())
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<NowPlayingMovie>()
        movie.value = dummy
        `when`(movieDbRepository.getDetailMovie(id)).thenReturn(movie)
        viewModel.getMovie.observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}