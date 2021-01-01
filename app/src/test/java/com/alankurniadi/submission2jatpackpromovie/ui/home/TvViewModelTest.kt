package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
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
class TvViewModelTest {

    private lateinit var viewmodel: TvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<List<NowAiringTv.Results>>

    @Before
    fun setUp() {
        viewmodel = TvViewModel(movieDbRepository)
    }

    @Test
    fun getAiringTvShow() {
        val tvShowList = MutableLiveData<List<NowAiringTv.Results>>()
        val dataDummy = DataDummy.getAiringTvShow()
        tvShowList.value = dataDummy

        `when`(movieDbRepository.getAiringTv()).thenReturn(tvShowList)
        viewmodel.getNowAiringTv()
        val dataTv = viewmodel.data
        verify<MovieDbRepository>(movieDbRepository).getAiringTv()

        assertNotNull(dataTv)
        assertEquals(21, dataTv.value?.size)

        viewmodel.data.observeForever(observer)
        verify(observer).onChanged(dataDummy)
    }
}