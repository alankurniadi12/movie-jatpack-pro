package com.alankurniadi.submission2jatpackpromovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TrendingResponse (
    var id: Int = 0,
    var backdrop_path: String? = null,
    var title: String? = null,
    var name: String? = null,
    var poster_path: String? = null,
    var vote_average: Double = 0.0,
    var release_date: String? = null,
    var first_air_date: String? = null,
    var overview: String? = null,
    var media_type: String? = null
        ):Parcelable