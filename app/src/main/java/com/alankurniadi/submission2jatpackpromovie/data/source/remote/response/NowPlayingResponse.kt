package com.alankurniadi.submission2jatpackpromovie.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NowPlayingResponse (
    var id: Int = 0,
    var backDrop: String? = null,
    var overView: String? = null,
    var poster: String? = null,
    var date: String? = null,
    var title: String? = null,
    var vote: Double = 0.0
        ): Parcelable