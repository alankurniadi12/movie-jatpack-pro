package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
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
class BookmarkMovieViewModelTest {

    private lateinit var viewModel: BookmarkMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<PagedList<NowPlayingMovie>>

    @Mock
    private lateinit var pagedList: PagedList<NowPlayingMovie>

    @Before
    fun setUp() {
        viewModel = BookmarkMovieViewModel(repository)
    }


    @Test
    fun getBookmark() {
        val dummy = pagedList
        `when`(dummy.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<NowPlayingMovie>>()
        movie.value = dummy

        `when`(repository.getBookmarkMovie()).thenReturn(movie)
        val data = viewModel.getBookmark().value
        verify(repository).getBookmarkMovie()
        assertNotNull(data)
        assertEquals(5, data?.size)
        viewModel.getBookmark().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}