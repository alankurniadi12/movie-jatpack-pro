package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
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
class BookmarkTvViewModelTest {

    private lateinit var viewModel: BookmarkTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<PagedList<NowAiringTv>>

    @Mock
    private lateinit var pagedList: PagedList<NowAiringTv>

    @Before
    fun setUp() {
        viewModel = BookmarkTvViewModel(repository)
    }

    @Test
    fun getBookmarkTv() {
        val dummy = pagedList
        `when`(dummy.size).thenReturn(5)
        val tvShow = MutableLiveData<PagedList<NowAiringTv>>()
        tvShow.value = dummy
        `when`(repository.getBookmarkTv()).thenReturn(tvShow)
        val data = viewModel.getBookmarkTv().value
        verify(repository).getBookmarkTv()
        assertNotNull(data)
        assertEquals(5, data?.size)

        viewModel.getBookmarkTv().observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}