package com.alankurniadi.submission2jatpackpromovie.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
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
    private lateinit var observer: Observer<NowPlayingMovie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieDbRepository)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<NowPlayingMovie>()
        movie.value = dummy

        `when`(movieDbRepository.getDetailMovie(id)).thenReturn(movie)
        val detail = viewModel.getMovie.value
        verify(movieDbRepository).getDetailMovie(id)

        assertNotNull(detail)
        assertEquals(detail?.id, dummy.id)
        assertEquals(detail?.backDrop, dummy.backDrop)
        assertEquals(detail?.title, dummy.title)
        assertEquals(detail?.overView, dummy.overView)
        assertEquals(detail?.poster, dummy.poster)
        assertEquals(detail?.date, dummy.date)
        assertEquals(detail?.vote, dummy.vote)

        viewModel.getMovie.observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}