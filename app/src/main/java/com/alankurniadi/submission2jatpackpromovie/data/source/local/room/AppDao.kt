package com.alankurniadi.submission2jatpackpromovie.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek

@Dao
interface AppDao {

    //DATA TRENDING ==============================>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataTrending(trending: List<TrendingWeek>)

    @Update
    fun updateTrending(trendingWeek: TrendingWeek)

    @Query("SELECT * FROM trending")
    fun getAllTrending(): DataSource.Factory<Int, TrendingWeek>

    @Query("SELECT * FROM trending WHERE id = :id")
    fun getDetailTrending(id: Int): LiveData<TrendingWeek>

    @Query("SELECT * FROM trending WHERE bookmarked = 1")
    fun getBookmarkTrending(): DataSource.Factory<Int, TrendingWeek>


    //MOVIE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataNowPlaying(nowPlayingMovie: List<NowPlayingMovie>)

    @Update
    fun updateDetailMovie(nowPlayingMovie: NowPlayingMovie)

    @Query("SELECT * FROM nowplaying")
    fun getAllNowPlaying(): DataSource.Factory<Int, NowPlayingMovie> //LiveData<List<NowPlayingMovie>>

    @Query("SELECT * FROM nowplaying WHERE id = :id")
    fun getDetailMovie(id: Int): LiveData<NowPlayingMovie> //LiveData<NowPlayingMovie>

    @Query("SELECT * FROM nowplaying WHERE bookmarked = 1")
    fun getBookmarkMovie(): DataSource.Factory<Int, NowPlayingMovie> //LiveData<List<NowPlayingMovie>>


    //TV
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataTv(nowAiringTv: List<NowAiringTv>)

    @Update
    fun updateDetailTv(nowAiringTv: NowAiringTv)

    @Query("SELECT * from nowairingtv")
    fun getAllAiringTv(): DataSource.Factory<Int, NowAiringTv>

    @Query("SELECT * from nowairingtv WHERE id = :id")
    fun getDetailTv(id: Int): LiveData<NowAiringTv>

    @Query("SELECT * FROM nowairingtv WHERE bookmarked = 1")
    fun getBookmarkTv(): DataSource.Factory<Int, NowAiringTv>

}