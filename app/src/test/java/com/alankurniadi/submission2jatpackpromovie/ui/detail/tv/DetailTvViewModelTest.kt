package com.alankurniadi.submission2jatpackpromovie.ui.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
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
class DetailTvViewModelTest {

    private lateinit var viewModel: DetailTvViewModel
    private val dummy = DataDummy.getTvShowDetail()[0]
    private val id = dummy.id

    @get:Rule
    var instanstTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieDbRepository: MovieDbRepository

    @Mock
    private lateinit var observer: Observer<NowAiringTv>

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(movieDbRepository)
    }

    @Test
    fun getDataDetailTv() {
        val tvShow = MutableLiveData<NowAiringTv>()
        tvShow.value = dummy

        `when`(movieDbRepository.getDetailTv(id)).thenReturn(tvShow)
        val detail = viewModel.getTv.value
        verify(movieDbRepository).getDetailTv(id)

        assertNotNull(detail)
        assertEquals(detail?.id, id)
        assertEquals(detail?.backDrop, dummy.backDrop)
        assertEquals(detail?.name, dummy.name)
        assertEquals(detail?.overview, dummy.overview)
        assertEquals(detail?.poster_path, dummy.poster_path)
        assertEquals(detail?.first_air_date, dummy.first_air_date)
        assertEquals(detail?.vote, dummy.vote)

        viewModel.getTv.observeForever(observer)
        verify(observer).onChanged(dummy)
    }
}