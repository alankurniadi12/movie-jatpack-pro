package com.alankurniadi.submission2jatpackpromovie.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie

class DetailMovieViewModel(private val movieDbRepository: MovieDbRepository): ViewModel() {
    private val detailMovieId = MutableLiveData<String>()

    fun setDetailMovielID(id: String) {
        this.detailMovieId.value = id
    }

    var getMovie: LiveData<NowPlayingMovie> = Transformations.switchMap(detailMovieId) {
        movieDbRepository.getDetailMovie(it.toInt())
    }

    fun setDetailMovieBookMark() {
        val mMovie = getMovie.value
        if (mMovie != null) {
            val newState = !mMovie.bookmarked
            movieDbRepository.setDetailMovieBookmark(mMovie, newState)
        }
    }
}