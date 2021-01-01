package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.ResponseResultTrending
import com.alankurniadi.submission2jatpackpromovie.utils.DataDummy
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
    private lateinit var observer: Observer<List<TrendingWeek>>

    @Before
    fun setUp() {
        viewModel = TrendingViewModel(movieDbRepository)
    }

    @Test
    fun getWeekTrending() {
        val trendingList = MutableLiveData<List<TrendingWeek>>()
        val dataDummy = DataDummy.getTrending()
        trendingList.value = dataDummy

        `when`(movieDbRepository.getTrendingWeek()).thenReturn(trendingList)
        viewModel.getTrendingWeek()
        val dataTrending = viewModel.data
        verify<MovieDbRepository>(movieDbRepository).getTrendingWeek()

        assertNotNull(dataTrending)
        assertEquals(21, dataTrending.value?.size)

        viewModel.data.observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }

}