    package com.alankurniadi.submission2jatpackpromovie.ui.detail.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.api.Url
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.databinding.ActivityDetailMovieBinding
import com.alankurniadi.submission2jatpackpromovie.ui.home.TrendingViewModel
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {

    private lateinit var viewModelMovie: DetailMovieViewModel
    private lateinit var viewModelTrending: TrendingViewModel
    private lateinit var binding: ActivityDetailMovieBinding
    private var menu: Menu? = null
    private var dataMovie: NowPlayingMovie? = null
    private var dataBookmark: NowPlayingMovie? = null
    private var dataTrending: TrendingWeek? = null

    companion object {
        const val FROM_TRENDING = "from_trending"
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_BOOKMARK = "extra_bookmark"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.tbTitleMovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataMovie = intent.getParcelableExtra(EXTRA_MOVIE)
        dataTrending = intent.getParcelableExtra(FROM_TRENDING)
        dataBookmark = intent.getParcelableExtra(EXTRA_BOOKMARK)


        val factory = ViewModelFactory.getInstance(this)
        viewModelTrending = ViewModelProvider(this, factory)[TrendingViewModel::class.java]
        viewModelMovie = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]
        if (dataTrending != null) {
            binding.tbTitleMovie.title = dataTrending?.title
            viewModelTrending.setDetailTrendingId(dataTrending?.id.toString())
            viewModelTrending.getDetailTrending.observe(this, {
                if (it != null) {
                    with(binding) {
                        tvTitleMovie.text = it.title
                        tvVote.text = it.vote_average.toString()
                        tvRelease.text = it.release_date
                        tvDetail.text = it.overview
                        Glide.with(detailActivityMovie)
                            .load(Url.BACKDROP_URL+it.backdrop_path)
                            .into(imgBackdroupMovie)
                        Glide.with(detailActivityMovie)
                            .load(Url.POSTER_URL+it.poster_path)
                            .into(imgPoster)
                    }
                }
            })

        } else if (dataMovie != null) {
            binding.tbTitleMovie.title = dataMovie?.title
            viewModelMovie.setDetailMovielID(dataMovie?.id.toString())
            viewModelMovie.getMovie.observe(this, {
                if (it != null) {
                    with(binding) {
                        tvTitleMovie.text = it.title
                        tvVote.text = it.vote.toString()
                        tvRelease.text = it.date
                        tvDetail.text = it.overView
                        Glide.with(detailActivityMovie)
                            .load(Url.BACKDROP_URL+it.backDrop)
                            .into(imgBackdroupMovie)
                        Glide.with(detailActivityMovie)
                            .load(Url.POSTER_URL+it.poster)
                            .into(imgPoster)
                    }
                }
            })
        } else {
            binding.tbTitleMovie.title = dataBookmark?.title
            viewModelMovie.setDetailMovielID(dataBookmark?.id.toString())
            viewModelMovie.getMovie.observe(this, {
                if (it != null) {
                    with(binding) {
                        tvTitleMovie.text = it.title
                        tvVote.text = it.vote.toString()
                        tvRelease.text = it.date
                        tvDetail.text = it.overView
                        Glide.with(detailActivityMovie)
                            .load(Url.BACKDROP_URL+it.backDrop)
                            .into(imgBackdroupMovie)
                        Glide.with(detailActivityMovie)
                            .load(Url.POSTER_URL+it.poster)
                            .into(imgPoster)
                    }
                }
            })
        }
    }



    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (dataTrending != null) {
            viewModelTrending.getDetailTrending.observe(this, { mDetail ->
                val state = mDetail.bookmarked
                setBookmarkState(state)
            })
        } else {
            viewModelMovie.getMovie.observe(this, { mDetail ->
                val state = mDetail.bookmarked
                setBookmarkState(state)
            })
        }

        return true
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_24)
        }else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_bookmark) {
            if (dataTrending != null) {
                viewModelTrending.setDetailTrendingBookmark()
            } else {
                viewModelMovie.setDetailMovieBookMark()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}
