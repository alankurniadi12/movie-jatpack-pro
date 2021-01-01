package com.alankurniadi.submission2jatpackpromovie.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "detailtv")
data class DetailTv(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "backdrop")
    var backdrop_path: String? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "poster")
    var poster_path: String? = null,

    @ColumnInfo(name = "date")
    var first_air_date: String? = null,

    @ColumnInfo(name = "vote")
    var vote_average: Double? = null
): Serializable