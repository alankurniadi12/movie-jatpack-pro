package com.alankurniadi.submission2jatpackpromovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.alankurniadi.submission2jatpackpromovie.data.source.MovieDbRepository
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.vo.Resource

class TvViewModel(private val movieDbRepository: MovieDbRepository): ViewModel() {

    fun getNowAiringTv(): LiveData<Resource<PagedList<NowAiringTv>>> = movieDbRepository.getAiringTv()
}