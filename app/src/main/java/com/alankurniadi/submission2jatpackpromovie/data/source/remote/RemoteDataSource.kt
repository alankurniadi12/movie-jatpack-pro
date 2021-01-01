package com.alankurniadi.submission2jatpackpromovie.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowAiringTvResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowPlayingResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.TrendingResponse
import com.alankurniadi.submission2jatpackpromovie.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(helper)
        }
    }

    fun getAllTrending(): LiveData<ApiResponse<List<TrendingResponse>>> {
        val resultTrendingWeek = MutableLiveData<ApiResponse<List<TrendingResponse>>>()
        val trendingObserver: Observer<List<TrendingResponse>> = object : Observer<List<TrendingResponse>> {
            override fun onChanged(t: List<TrendingResponse>) {
                resultTrendingWeek.value = ApiResponse.success(t)
                jsonHelper.loadTrendingWeek().removeObserver(this)
            }
        }
        jsonHelper.loadTrendingWeek().observeForever(trendingObserver)
        return resultTrendingWeek
    }

    fun getAllNowPlaying(): LiveData<ApiResponse<List<NowPlayingResponse>>> {
        val resultNowPlayingMovie = MutableLiveData<ApiResponse<List<NowPlayingResponse>>>()
        val playingObserver: Observer<List<NowPlayingResponse>> = object : Observer<List<NowPlayingResponse>> {
            override fun onChanged(t: List<NowPlayingResponse>) {
                resultNowPlayingMovie.value = ApiResponse.success(t)
                jsonHelper.loadNowPlayingMovie().removeObserver(this)
            }
        }
        jsonHelper.loadNowPlayingMovie().observeForever(playingObserver)
        return resultNowPlayingMovie
    }

    fun getNowAiringTv(): LiveData<ApiResponse<List<NowAiringTvResponse>>> {
        val resultNowAiringTv = MutableLiveData<ApiResponse<List<NowAiringTvResponse>>>()
        val tvObserver: Observer<List<NowAiringTvResponse>> = object : Observer<List<NowAiringTvResponse>> {
            override fun onChanged(t: List<NowAiringTvResponse>) {
                resultNowAiringTv.value = ApiResponse.success(t)
                jsonHelper.loadTvShowToDay().removeObserver(this)
            }
        }
        jsonHelper.loadTvShowToDay().observeForever(tvObserver)
        return resultNowAiringTv
    }
}