package com.alankurniadi.submission2jatpackpromovie.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.*

@Database(entities = [DetailMovie::class,
    DetailTv::class,
    NowAiringTv::class,
    NowPlayingMovie::class,
    TrendingWeek::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "SubAkhirBajp.db").build()
        }
    }
}