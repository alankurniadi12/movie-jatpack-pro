package com.alankurniadi.submission2jatpackpromovie.di

import android.content.Context
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.LocalDataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.remote.RemoteDataSource
import com.alankurniadi.submission2jatpackpromovie.data.source.local.room.AppDatabase
import com.alankurniadi.submission2jatpackpromovie.utils.AppExecutors
import com.alankurniadi.submission2jatpackpromovie.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieDbRepository {

        val database = AppDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()
        return MovieDbRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}