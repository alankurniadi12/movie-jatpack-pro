package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
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
class TvViewModelTest {

    private lateinit var viewmodel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<NowAiringTv>>>

    @Mock
    private lateinit var pagedList: PagedList<NowAiringTv>

    @Before
    fun setUp() {
        viewmodel = TvViewModel(movieDbRepository)
    }

    @Test
    fun getAiringTvShow() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(5)
        val tvShow = MutableLiveData<Resource<PagedList<NowAiringTv>>>()
        tvShow.value = dummyTv


        `when`(movieDbRepository.getAiringTv()).thenReturn(tvShow)
        val mTvShow = viewmodel.data.value?.data
        verify(movieDbRepository).getAiringTv()
        assertNotNull(mTvShow)
        assertEquals(5, mTvShow?.size)

        viewmodel.data.observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}