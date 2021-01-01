package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie

class BookmarkMovieViewModel(private val repository: MovieDbRepository): ViewModel() {

    fun getBookmark(): LiveData<PagedList<NowPlayingMovie>> =
        repository.getBookmarkMovie()
}