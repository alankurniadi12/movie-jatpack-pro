package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeekViewModelTest {

    private lateinit var viewModel: TrendingViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TrendingWeek>>>

    @Mock
    private lateinit var pagedList: PagedList<TrendingWeek>

    @Before
    fun setUp() {
        viewModel = TrendingViewModel(movieDbRepository)
    }

    @Test
    fun getWeekTrending() {
        val dataDummy = Resource.success(pagedList)
        `when`(dataDummy.data?.size).thenReturn(21)
        val trending = MutableLiveData<Resource<PagedList<TrendingWeek>>>()
        trending.value = dataDummy

        `when`(movieDbRepository.getTrendingWeek()).thenReturn(trending)
        val mTrending = viewModel.data.value?.data
        verify(movieDbRepository).getTrendingWeek()
        assertNotNull(mTrending)
        assertEquals(21, mTrending?.size)

        viewModel.data.observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }

}