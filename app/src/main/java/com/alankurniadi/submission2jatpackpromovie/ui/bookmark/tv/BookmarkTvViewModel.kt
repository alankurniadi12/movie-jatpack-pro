package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv

class BookmarkTvViewModel(private val repository: MovieDbRepository): ViewModel() {

    fun getBookmarkTv(): LiveData<PagedList<NowAiringTv>> {
        return repository.getBookmarkTv()
    }
}