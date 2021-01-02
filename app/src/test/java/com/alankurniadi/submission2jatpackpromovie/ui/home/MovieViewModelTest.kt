package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.vo.Resource
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
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<NowPlayingMovie>>>

    @Mock
    private lateinit var pagedList: PagedList<NowPlayingMovie>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieDbRepository)
    }


    @Test
    fun getNowPlayingMovie() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(5)
        val movie = MutableLiveData<Resource<PagedList<NowPlayingMovie>>>()
        movie.value = dummyMovie

        `when`(movieDbRepository.getNowPlayingMovie()).thenReturn(movie)
        val mMovie = viewModel.data.value?.data
        verify(movieDbRepository).getNowPlayingMovie()
        assertNotNull(mMovie)
        assertEquals(5, mMovie?.size)

        viewModel.data.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}