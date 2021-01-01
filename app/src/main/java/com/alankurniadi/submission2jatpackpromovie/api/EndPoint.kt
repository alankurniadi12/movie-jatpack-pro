package com.alankurniadi.submission2jatpackpromovie.api

import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.ResponseResultTrending
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {

    // Home
    @GET("trending/all/week?api_key=${Url.API_KEY}")
    fun getTrendingWeek() : Call<ResponseResultTrending>

    /*@GET("movie/now_playing?api_key=${Url.API_KEY}")
    fun getNowPlayingMovie() : Call<NowPlayingResponse.ResponseNowPlaying>*/

    /*@GET("tv/airing_today?api_key=${Url.API_KEY}")
    fun getNowAiringTv(): Call<NowAiringTvResponse.ResponseAiringTv>*/

    // Detail Movie
    /*@GET("movie/{movie_id}?api_key=${Url.API_KEY}")
    fun getDetailMovie(
        @Path("movie_id") id: Int
    ): Call<DetailMovieResponse>*/

    // Detail Tv
    /*@GET("tv/{id}?api_key=${Url.API_KEY}")
    fun getDetailTv(
        @Path("id") id: Int?
    ): Call<DetailTvResponse>*/


}