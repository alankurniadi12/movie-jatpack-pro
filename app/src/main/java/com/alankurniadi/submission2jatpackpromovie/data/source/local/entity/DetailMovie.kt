package com.alankurniadi.submission2jatpackpromovie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "detailmovie")
data class DetailMovie(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "backdrop path")
    var backdrop_path: String? = null,
    @ColumnInfo(name = "title")
    var title: String? = null,
    @ColumnInfo(name = "overview")
    var overview: String? = null,
    @ColumnInfo(name = "poster path")
    var poster_path: String? = null,
    @ColumnInfo(name = "release date")
    var release_date: String? = null,
    @ColumnInfo(name = "vote average")
    var vote_average: Double? = null,
): Serializable
