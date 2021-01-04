package com.alankurniadi.submission2jatpackpromovie.api

import com.alankurniadi.submission2jatpackpromovie.data.source.remote.response.ResponseResultTrending
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {

    // Home
    @GET("trending/all/week?api_key=${Url.API_KEY}")
    fun getTrendingWeek() : Call<ResponseResultTrending>
}