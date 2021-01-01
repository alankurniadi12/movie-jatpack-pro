package com.alankurniadi.submission2jatpackpromovie.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "nowplaying")
@Parcelize
data class NowPlayingMovie (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "backdrop")
    var backDrop: String? = null,

    @ColumnInfo(name = "overview")
    var overView: String? = null,

    @ColumnInfo(name = "poster")
    var poster: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "vote")
    var vote: Double = 0.0,

    @ColumnInfo(name = "bookmarked")
    var bookmarked: Boolean = false
        ): Parcelable
