package com.alankurniadi.submission2jatpackpromovie.data.source.remote.response

import java.io.Serializable

data class DetailMovieResponse (
    var id: Int = 0,
    var backdrop_path: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var vote_average: Double? = null
        ): Serializable