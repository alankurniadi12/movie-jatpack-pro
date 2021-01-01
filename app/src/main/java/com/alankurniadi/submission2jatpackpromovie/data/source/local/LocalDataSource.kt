package com.alankurniadi.submission2jatpackpromovie.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.data.source.local.room.AppDao

class LocalDataSource private constructor(private val appDao: AppDao) {
    companion object {
        private  var INSTANCE: LocalDataSource? = null

        fun getInstance(appDao: AppDao): LocalDataSource = INSTANCE ?: LocalDataSource(appDao)
    }

    //TRENDING
    fun insertTrending(trendingWeek: List<TrendingWeek>) = appDao.insertDataTrending(trendingWeek)

    fun setBookMarkTrending(trendingWeek: TrendingWeek, newState: Boolean) {
        trendingWeek.bookmarked = newState
        appDao.updateTrending(trendingWeek)
    }

    fun getAllTrending(): DataSource.Factory<Int, TrendingWeek> = appDao.getAllTrending()

    fun getDetailTrending(id: Int): LiveData<TrendingWeek> = appDao.getDetailTrending(id)

    fun getBookmarkTrending(): DataSource.Factory<Int, TrendingWeek> = appDao.getBookmarkTrending()


    //MOVIE
    fun insertNowPlaying(nowPlayingMovie: List<NowPlayingMovie>) = appDao.insertDataNowPlaying(nowPlayingMovie)

    fun setBookMarkDetailMovie(nowPlayingMovie: NowPlayingMovie, newState: Boolean) {
        nowPlayingMovie.bookmarked = newState
        appDao.updateDetailMovie(nowPlayingMovie)
    }

    fun getAllNowPlaying(): DataSource.Factory<Int, NowPlayingMovie> = appDao.getAllNowPlaying()

    fun getDetailMovie(id: Int): LiveData<NowPlayingMovie> = appDao.getDetailMovie(id)

    fun getBookmarkMovie(): DataSource.Factory<Int, NowPlayingMovie> = appDao.getBookmarkMovie()


    //TV
    fun getAllAiringTv(): DataSource.Factory<Int, NowAiringTv> = appDao.getAllAiringTv()

    fun setBookmarkDetailTv(nowAiringTv: NowAiringTv, newState: Boolean) {
        nowAiringTv.bookmarked = newState
        appDao.updateDetailTv(nowAiringTv)
    }

    fun insertAiringTv(nowAiringTv: List<NowAiringTv>) = appDao.insertDataTv(nowAiringTv)

    fun getAllDetailTv(id: Int): LiveData<NowAiringTv> = appDao.getDetailTv(id)

    fun getBookmarkTv(): DataSource.Factory<Int, NowAiringTv> = appDao.getBookmarkTv()

}