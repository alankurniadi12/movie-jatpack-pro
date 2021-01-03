package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.nhaarman.mockitokotlin2.verify
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
class BookmarkTrendingViewModelTest {

    private lateinit var viewModel: BookmarkTrendingViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TrendingWeek>>

    @Mock
    private lateinit var pagedList: PagedList<TrendingWeek>

    @Before
    fun setUp() {
        viewModel = BookmarkTrendingViewModel(repository)
    }

    @Test
    fun getBookmarkTrending() {
        val dummy = pagedList
        `when`(dummy.size).thenReturn(5)
        val trending = MutableLiveData<PagedList<TrendingWeek>>()
        trending.value = dummy

        `when`(repository.getBookmarkTrending()).thenReturn(trending)
        val data = viewModel.getBookmarkTrending().value
        verify(repository).getBookmarkTrending()
        assertNotNull(data)
        assertEquals(5, data?.size)

        viewModel.getBookmarkTrending().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}