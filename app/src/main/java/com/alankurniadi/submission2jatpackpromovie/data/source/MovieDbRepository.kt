package com.alankurniadi.submission2jatpackpromovie.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.NetworkBoundResource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.LocalDataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.ApiResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.RemoteDataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowAiringTvResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.NowPlayingResponse
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.TrendingResponse
import com.alankurniadi.submission2jatpackpromovie.utils.AppExecutors
import com.alankurniadi.submission2jatpackpromovie.vo.Resource

class MovieDbRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): MovieDbDataSource {

    companion object {
        @Volatile
        private var instance: MovieDbRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieDbRepository =
            instance ?: synchronized(this) {
            instance ?: MovieDbRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    //TRENDING
    override fun getTrendingWeek(): LiveData<Resource<PagedList<TrendingWeek>>> {
        return object : NetworkBoundResource<PagedList<TrendingWeek>, List<TrendingResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TrendingWeek>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1)
                    .setPageSize(1)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTrending(), config).build()
            }

            override fun shouldFetch(data: PagedList<TrendingWeek>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TrendingResponse>>> {
                val dataList = remoteDataSource.getAllTrending()
                return dataList
            }
            override fun saveCallResult(data: List<TrendingResponse>) {
                val weekTrendingResult = ArrayList<TrendingWeek>()
                for (response in data) {
                    val result = TrendingWeek(
                        response.id,
                        response.backdrop_path,
                        response.title,
                        response.name,
                        response.poster_path,
                        response.vote_average,
                        response.release_date,
                        response.first_air_date,
                        response.overview,
                        response.media_type)

                    weekTrendingResult.add(result)
                }
                localDataSource.insertTrending(weekTrendingResult)
            }
        }.asLiveData()
    }

    override fun getDetailTrending(id: Int): LiveData<TrendingWeek> = localDataSource.getDetailTrending(id)

    override fun setDetailBookmarkTrending(trendingWeek: TrendingWeek, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookMarkTrending(trendingWeek, state) }

    override fun getBookmarkTrending(): LiveData<PagedList<TrendingWeek>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkTrending(), config).build()
    }


    //MOVIE
    override fun getNowPlayingMovie(): LiveData<Resource<PagedList<NowPlayingMovie>>> {
        return object : NetworkBoundResource<PagedList<NowPlayingMovie>, List<NowPlayingResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<NowPlayingMovie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1)
                    .setPageSize(1)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllNowPlaying(), config).build()
            }

            override fun shouldFetch(data: PagedList<NowPlayingMovie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<NowPlayingResponse>>> =
                remoteDataSource.getAllNowPlaying()

            override fun saveCallResult(data: List<NowPlayingResponse>) {
                val list = ArrayList<NowPlayingMovie>()
                for (response in data) {
                    val result = NowPlayingMovie(
                        response.id,
                        response.backDrop,
                        response.overView,
                        response.poster,
                        response.date,
                        response.title,
                        response.vote)
                    list.add(result)
                }
                localDataSource.insertNowPlaying(list)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(id: Int): LiveData<NowPlayingMovie> = localDataSource.getDetailMovie(id)

    override fun setDetailMovieBookmark(nowPlayingMovie: NowPlayingMovie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setBookMarkDetailMovie(nowPlayingMovie, state) }

    override fun getBookmarkMovie(): LiveData<PagedList<NowPlayingMovie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkMovie(), config).build()
    }


    //TV
    override fun getAiringTv(): LiveData<Resource<PagedList<NowAiringTv>>> {
        return object : NetworkBoundResource<PagedList<NowAiringTv>, List<NowAiringTvResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<NowAiringTv>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(1)
                    .setPageSize(1)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllAiringTv(), config).build()
            }

            override fun shouldFetch(data: PagedList<NowAiringTv>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<NowAiringTvResponse>>> =
                remoteDataSource.getNowAiringTv()

            override fun saveCallResult(data: List<NowAiringTvResponse>) {
                val list = ArrayList<NowAiringTv>()
                for (response in data) {
                    val result = NowAiringTv(
                        response.id,
                        response.backDrop,
                        response.first_air_date,
                        response.name,
                        response.overview,
                        response.poster_path,
                        response.vote)
                    list.add(result)
                }
                localDataSource.insertAiringTv(list)
            }
        }.asLiveData()
    }

    override fun getDetailTv(id: Int): LiveData<NowAiringTv> = localDataSource.getAllDetailTv(id)

    override fun setDetailTvBookmark(nowAiringTv: NowAiringTv, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setBookmarkDetailTv(nowAiringTv, state) }
    }

    override fun getBookmarkTv(): LiveData<PagedList<NowAiringTv>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(1)
            .setPageSize(1)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkTv(), config).build()
    }
}
