package com.alankurniadi.submission2jatpackpromovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "trending")
@Parcelize
data class TrendingWeek (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "backdroppath")
    var backdrop_path: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "poster_path")
    var poster_path: String? = null,

    @ColumnInfo(name = "vote_average")
    var vote_average: Double = 0.0,

    @ColumnInfo(name = "release_date")
    var release_date: String? = null,

    @ColumnInfo(name = "first_air_date")
    var first_air_date: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "media_type")
    var media_type: String? = null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
        ): Parcelable


