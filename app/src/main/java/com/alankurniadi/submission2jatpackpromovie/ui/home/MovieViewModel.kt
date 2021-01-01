package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.vo.Resource

class MovieViewModel(private val movieDbRepository: MovieDbRepository): ViewModel() {

    lateinit var data: LiveData<Resource<PagedList<NowPlayingMovie>>>

    fun getNowPlayingMovie() {
        data = movieDbRepository.getNowPlayingMovie()
    }
}