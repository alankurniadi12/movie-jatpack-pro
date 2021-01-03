package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.vo.Resource

class TrendingViewModel(private val movieDbRepository: MovieDbRepository) : ViewModel() {

    val trendingId = MutableLiveData<String>()

    fun setDetailTrendingId(id: String) {
        this.trendingId.value = id
    }

    var getDetailTrending: LiveData<TrendingWeek> = Transformations.switchMap(trendingId) { id ->
        movieDbRepository.getDetailTrending(id.toInt())
    }

    fun getTrendingWeek(): LiveData<Resource<PagedList<TrendingWeek>>> = movieDbRepository.getTrendingWeek()

    fun setDetailTrendingBookmark() {
        val mTrending = getDetailTrending.value
        if (mTrending != null) {
            val newState = !mTrending.bookmarked
            movieDbRepository.setDetailBookmarkTrending(mTrending, newState)
        }
    }

}