package com.alankurniadi.submission2jatpackpromovie.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.LocalDataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.RemoteDataSource
import com.alankurniadi.submission2jatpackpromovie.ui.utils.LiveDataTestUtil
import com.alankurniadi.submission2jatpackpromovie.ui.utils.PagedListUtil
import com.alankurniadi.submission2jatpackpromovie.utils.AppExecutors
import com.alankurniadi.submission2jatpackpromovie.utils.DataDummy
import com.alankurniadi.submission2jatpackpromovie.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieDbRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteSource = mock(RemoteDataSource::class.java)
    private val localSource = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val repository = FakeMovieDbRepository(remoteSource, localSource, appExecutors)

    private val trendingResponse = DataDummy.getTrendingRemote()
    private val idTrending = trendingResponse[0].id
    private val movieResponse = DataDummy.getNowPlayingMovieRemote()
    private val idMovie = movieResponse[0].id
    private val tvResponse = DataDummy.getAiringTvShowRemote()
    private val idTvShow = tvResponse[0].id


    //=============== TRENDING =================
    @Test
    fun getTrendingWeek() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TrendingWeek>
        `when`(localSource.getAllTrending()).thenReturn(dataSourceFactory)
        repository.getTrendingWeek()

        val trendingWeek = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTrending()))
        verify(localSource).getAllTrending()
        assertNotNull(trendingWeek)
        assertEquals(trendingResponse.size.toLong(), trendingWeek.data?.size?.toLong())
    }

    @Test
    fun getDetailTrending() {
        val detailTrendingDummy = MutableLiveData<TrendingWeek>()
        detailTrendingDummy.value = DataDummy.getTrending()[0]
        `when`(localSource.getDetailTrending(idTrending)).thenReturn(detailTrendingDummy)

        val mTrending = LiveDataTestUtil.getValue(repository.getDetailTrending(idTrending))
        verify(localSource).getDetailTrending(idTrending)
        assertNotNull(mTrending)
        assertNotNull(mTrending.id)
        assertEquals(trendingResponse[0].id, mTrending.id)
    }

    @Test
    fun getBookmarkTrending() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TrendingWeek>
        `when`(localSource.getBookmarkTrending()).thenReturn(dataSourceFactory)
        repository.getBookmarkTrending()

        val bookmarkTrending = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTrending()))
        verify(localSource).getBookmarkTrending()
        assertNotNull(bookmarkTrending)
        assertEquals(trendingResponse.size.toLong(), bookmarkTrending.data?.size?.toLong())
    }

    //=============== MOVIE =================
    @Test
    fun getNowPlayingMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NowPlayingMovie>
        `when`(localSource.getAllNowPlaying()).thenReturn(dataSourceFactory)
        repository.getNowPlayingMovie()

        val movie = Resource.success(PagedListUtil.mockPagedList(DataDummy.getNowPlayingMovie()))
        verify(localSource).getAllNowPlaying()
        assertNotNull(movie)
        assertEquals(movieResponse.size.toLong(), movie.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val detailMovieDummy = MutableLiveData<NowPlayingMovie>()
        detailMovieDummy.value = DataDummy.getNowPlayingMovie()[0]
        `when`(localSource.getDetailMovie(idMovie)).thenReturn(detailMovieDummy)

        val detailMovie = LiveDataTestUtil.getValue(repository.getDetailMovie(idMovie))
        verify(localSource).getDetailMovie(idMovie)
        assertNotNull(detailMovie)
        assertNotNull(detailMovie.id)
        assertEquals(movieResponse[0].id, detailMovie.id )
    }

    @Test
    fun getBookmarkMovie() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NowPlayingMovie>
        `when`(localSource.getBookmarkMovie()).thenReturn(dataSourceFactory)
        repository.getBookmarkMovie()

        val bookmarkMovie = Resource.success(PagedListUtil.mockPagedList(DataDummy.getNowPlayingMovie()))
        verify(localSource).getBookmarkMovie()
        assertNotNull(bookmarkMovie)
        assertEquals(movieResponse.size.toLong(), bookmarkMovie.data?.size?.toLong())
    }

    //=============== TV SHOW =================
    @Test
    fun getAiringTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NowAiringTv>
        `when`(localSource.getAllAiringTv()).thenReturn(dataSourceFactory)
        repository.getAiringTv()

        val tvShow = Resource.success(PagedListUtil.mockPagedList(DataDummy.getAiringTvShow()))
        verify(localSource).getAllAiringTv()
        assertNotNull(tvShow)
        assertEquals(tvResponse.size.toLong(), tvShow.data?.size?.toLong())
    }

    @Test
    fun getDetailTv() {
        val detailTvDummy = MutableLiveData<NowAiringTv>()
        detailTvDummy.value = DataDummy.getAiringTvShow()[0]
        `when`(localSource.getAllDetailTv(idTvShow)).thenReturn(detailTvDummy)

        val detailTvShow = LiveDataTestUtil.getValue(repository.getDetailTv(idTvShow))
        verify(localSource).getAllDetailTv(idTvShow)
        assertNotNull(detailTvShow)
        assertNotNull(detailTvShow.id)
        assertEquals(tvResponse[0].id, detailTvShow.id )
    }

    @Test
    fun getBookmarkTv() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, NowAiringTv>
        `when`(localSource.getBookmarkTv()).thenReturn(dataSourceFactory)
        repository.getBookmarkTv()

        val bookmarkTvShow = Resource.success(PagedListUtil.mockPagedList(DataDummy.getAiringTvShow()))
        verify(localSource).getBookmarkTv()
        assertNotNull(bookmarkTvShow)
        assertEquals(tvResponse.size.toLong(), bookmarkTvShow.data?.size?.toLong())
    }
}