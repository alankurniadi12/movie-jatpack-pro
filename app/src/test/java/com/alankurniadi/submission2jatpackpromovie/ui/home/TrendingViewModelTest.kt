package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.vo.Resource
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TrendingViewModelTest {

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
    fun getTrendingWeek() {
        val dataDummy = Resource.success(pagedList)
        Mockito.`when`(dataDummy.data?.size).thenReturn(5)
        val trending = MutableLiveData<Resource<PagedList<TrendingWeek>>>()
        trending.value = dataDummy

        Mockito.`when`(movieDbRepository.getTrendingWeek()).thenReturn(trending)
        val mTrending = viewModel.getTrendingWeek().value?.data
        Mockito.verify(movieDbRepository).getTrendingWeek()
        Assert.assertNotNull(mTrending)
        Assert.assertEquals(5, mTrending?.size)

        viewModel.getTrendingWeek().observeForever(observer)
        Mockito.verify(observer).onChanged(dataDummy)
    }
}