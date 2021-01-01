package com.alankurniadi.submission2jatpackpromovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.vo.Resource

interface MovieDbDataSource {

    //TRENDING
    fun getTrendingWeek(): LiveData<Resource<PagedList<TrendingWeek>>>

    fun getDetailTrending(id: Int): LiveData<TrendingWeek>

    fun setDetailBookmarkTrending(trendingWeek: TrendingWeek, state: Boolean)

    fun getBookmarkTrending(): LiveData<PagedList<TrendingWeek>>


    //MOVIE
    fun getNowPlayingMovie(): LiveData<Resource<PagedList<NowPlayingMovie>>>

    fun getDetailMovie(id: Int): LiveData<NowPlayingMovie>

    fun setDetailMovieBookmark(nowPlayingMovie: NowPlayingMovie, state: Boolean)

    fun getBookmarkMovie():LiveData<PagedList<NowPlayingMovie>>


    //TV
    fun getAiringTv(): LiveData<Resource<PagedList<NowAiringTv>>>

    fun getDetailTv(id: Int): LiveData<NowAiringTv>

    fun setDetailTvBookmark(nowAiringTv: NowAiringTv, state: Boolean)

    fun getBookmarkTv(): LiveData<PagedList<NowAiringTv>>
}