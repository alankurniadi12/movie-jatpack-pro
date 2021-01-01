package com.alankurniadi.submission2jatpackpromovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "nowairingtv")
@Parcelize
data class NowAiringTv (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    var backDrop: String? = null,

    @ColumnInfo(name = "date")
    var first_air_date: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster")
    var poster_path: String? = null,

    @ColumnInfo(name = "vote")
    var vote: Double? = null,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
        ):Parcelable