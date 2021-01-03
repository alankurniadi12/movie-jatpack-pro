package com.alankurniadi.submission2jatpackpromovie.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.databinding.ActivityMainBinding
import com.alankurniadi.submission2jatpackpromovie.ui.bookmark.BookmarkActivity
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory
import com.alankurniadi.submission2jatpackpromovie.vo.Status

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var menu: Menu? = null
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var trendingAdapter: TrendingAdapter
    private lateinit var tvAdapter: TvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar2)

        val factory = ViewModelFactory.getInstance(this)

        //TRENDING
        val viewModelWeek = ViewModelProvider(this, factory)[TrendingViewModel::class.java]
        trendingAdapter = TrendingAdapter(this)
        viewModelWeek.getTrendingWeek().observe(this, Observer {
            if (it != null) {
                when(it.status) {
                    Status.LOADING -> binding.progressBarWeek.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressBarWeek.visibility = View.GONE
                        trendingAdapter.submitList(it.data)
                        trendingAdapter.notifyDataSetChanged()
                        binding.rvThisWeek.adapter = trendingAdapter
                        binding.rvThisWeek.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    }
                    Status.ERROR -> {
                        binding.progressBarWeek.visibility = View.GONE
                        showToast("Terjadi Kesalahan - Trending")
                    }
                }

            }
        })

        //MOVIE
        val viewmodelMovie = ViewModelProvider(this, factory)[MovieViewModel::class.java]
        movieAdapter = MovieAdapter(this)
        viewmodelMovie.getNowPlayingMovie().observe(this, Observer {
            if (it != null) {
                when(it.status) {
                    Status.LOADING -> binding.progressMovie.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressMovie.visibility = View.GONE
                        movieAdapter.submitList(it.data)
                        movieAdapter.notifyDataSetChanged()
                        binding.rvMovie.adapter = movieAdapter
                        binding.rvMovie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    }
                    Status.ERROR -> {
                        binding.progressMovie.visibility = View.GONE
                        showToast("Terjadi Kesalahan - Movie")
                    }
                }
            }
        })

        //TV
        val viewModelTv = ViewModelProvider(this, factory)[TvViewModel::class.java]
        viewModelTv.getNowAiringTv()
        tvAdapter = TvAdapter(this)
        //EspressoIdlingResource.increment()
        viewModelTv.data.observe(this, Observer {
            if (it != null) {
                when(it.status) {
                    Status.LOADING -> binding.progressTv.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressTv.visibility = View.GONE
                        tvAdapter.submitList(it.data)
                        tvAdapter.notifyDataSetChanged()
                        binding.rvTv.adapter = tvAdapter
                        binding.rvTv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    }
                    Status.ERROR -> {
                        binding.progressTv.visibility = View.GONE
                        showToast("Terjadi Kesalahan - Tv")
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        this.menu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_bookmark -> {
                val intent = Intent(this, BookmarkActivity::class.java)
                startActivity(intent)
                true
            }
            else -> true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
