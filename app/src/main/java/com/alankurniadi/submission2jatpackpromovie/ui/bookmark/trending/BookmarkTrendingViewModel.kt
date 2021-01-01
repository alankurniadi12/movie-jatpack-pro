package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek

class BookmarkTrendingViewModel(private val repository: MovieDbRepository): ViewModel() {

    fun getBookmarkTrending(): LiveData<PagedList<TrendingWeek>> =
        repository.getBookmarkTrending()
}