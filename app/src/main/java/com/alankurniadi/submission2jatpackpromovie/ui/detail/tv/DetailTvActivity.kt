package com.alankurniadi.submission2jatpackpromovie.ui.detail.tv

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.api.Url
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.databinding.ActivityDetailTvBinding
import com.alankurniadi.submission2jatpackpromovie.ui.home.TrendingViewModel
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailTvActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvBinding
    private lateinit var viewModelTv: DetailTvViewModel
    private lateinit var viewModelTrending: TrendingViewModel
    private var menu: Menu? = null
    private var dataTrending: TrendingWeek? = null
    private var dataTvshow: NowAiringTv? = null
    private var dataBookmark: NowAiringTv? = null
    companion object {
        const val FROM_TRENDING = "from_trending"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_BOOKMARK_TV = "extra_bookmark_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.tbTitleTv)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dataTvshow = intent.getParcelableExtra(EXTRA_TV)
        dataTrending = intent.getParcelableExtra(FROM_TRENDING)
        dataBookmark = intent.getParcelableExtra(EXTRA_BOOKMARK_TV)

        val factory = ViewModelFactory.getInstance(this)
        viewModelTrending = ViewModelProvider(this, factory)[TrendingViewModel::class.java]
        viewModelTv = ViewModelProvider(this, factory)[DetailTvViewModel::class.java]
        if (dataTrending != null) {
            binding.tbTitleTv.title = dataTrending?.name
            viewModelTrending.setDetailTrendingId(dataTrending?.id.toString())
            viewModelTrending.getDetailTrending.observe(this, Observer {
                if (it != null) {
                    with(binding) {
                        progressBarTv.visibility = View.GONE
                        tvTitleTv.text = it.name
                        tvVoteTv.text = it.vote_average.toString()
                        tvReleaseTv.text = it.first_air_date
                        tvDetailTv.text = it.overview
                        Glide.with(detailActivityTv)
                            .load(Url.BACKDROP_URL+it.backdrop_path)
                            .into(imgBackdrop)
                        Glide.with(detailActivityTv)
                            .load(Url.POSTER_URL+it.poster_path)
                            .into(imgPosterTv)
                    }
                }
            })
        } else if (dataTvshow != null){
            binding.tbTitleTv.title = dataTvshow?.name
            viewModelTv.setDetailID(dataTvshow?.id.toString())
            viewModelTv.getTv.observe(this, Observer {
                if (it != null) {
                    with(binding) {
                        progressBarTv.visibility = View.GONE
                        tvTitleTv.text = it.name
                        tvVoteTv.text = it.vote.toString()
                        tvReleaseTv.text = it.first_air_date
                        tvDetailTv.text = it.overview
                        Glide.with(detailActivityTv)
                            .load(Url.BACKDROP_URL+it.backDrop)
                            .into(imgBackdrop)
                        Glide.with(detailActivityTv)
                            .load(Url.POSTER_URL+it.poster_path)
                            .into(imgPosterTv)
                    }
                }
            })
        }else {
            binding.tbTitleTv.title = dataBookmark?.name
            viewModelTv.setDetailID(dataBookmark?.id.toString())
            viewModelTv.getTv.observe(this, Observer {
                if (it != null) {
                    with(binding) {
                        progressBarTv.visibility = View.GONE
                        tvTitleTv.text = it.name
                        tvVoteTv.text = it.vote.toString()
                        tvReleaseTv.text = it.first_air_date
                        tvDetailTv.text = it.overview
                        Glide.with(detailActivityTv)
                            .load(Url.BACKDROP_URL+it.backDrop)
                            .into(imgBackdrop)
                        Glide.with(detailActivityTv)
                            .load(Url.POSTER_URL+it.poster_path)
                            .into(imgPosterTv)
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
            viewModelTrending.getDetailTrending.observe(this, {mDetail ->
                val state = mDetail.bookmarked
                setBookmarkState(state)
            })
        }else {
            viewModelTv.getTv.observe(this, { mDetail ->
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
            }else {
                viewModelTv.setDetailTvBookmark()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}