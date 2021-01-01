package com.alankurniadi.submission2jatpackpromovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class NowAiringTvResponse (
    var id: Int = 0,
    var backDrop: String? = null,
    var first_air_date: String? = null,
    var name: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var vote: Double? = null
        ): Parcelable
