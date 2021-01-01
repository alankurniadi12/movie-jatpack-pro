package com.alankurniadi.submission2jatpackpromovie.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private fun getInitTrending(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getInitInstance(): EndPoint {
        return getInitTrending().create(EndPoint::class.java)
    }
}

