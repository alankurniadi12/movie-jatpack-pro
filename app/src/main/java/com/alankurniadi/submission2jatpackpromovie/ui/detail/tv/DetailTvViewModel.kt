package com.alankurniadi.submission2jatpackpromovie.ui.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv

class DetailTvViewModel(private val movieDbRepository: MovieDbRepository): ViewModel() {
    private val detailID = MutableLiveData<String>()

    fun setDetailID(id: String) {
        this.detailID.value = id
    }

    var getTv: LiveData<NowAiringTv> = Transformations.switchMap(detailID) {
        movieDbRepository.getDetailTv(it.toInt())
    }

    fun setDetailTvBookmark() {
        val mTv = getTv.value
        if (mTv != null) {
            val newState = !mTv.bookmarked
            movieDbRepository.setDetailTvBookmark(mTv, newState)
        }
    }

}