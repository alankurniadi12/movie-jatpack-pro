package com.alankurniadi.submission2jatpackpromovie.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.api.Url
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemCardMainBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.movie.DetailMovieActivity
import com.bumptech.glide.Glide

class MovieAdapter internal constructor(private val activity: Activity): PagedListAdapter<NowPlayingMovie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NowPlayingMovie>() {
            override fun areItemsTheSame(
                oldItem: NowPlayingMovie,
                newItem: NowPlayingMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: NowPlayingMovie,
                newItem: NowPlayingMovie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemCardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }
    }

    inner class MovieViewHolder(private val binding: ItemCardMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nowPlaying: NowPlayingMovie) {
            with(itemView) {
                Glide.with(this)
                    .load(Url.POSTER_URL+nowPlaying.poster)
                    .error(R.drawable.ic_error)
                    .into(binding.imgPosterMain)
                binding.tvTitleMain.text = nowPlaying.title
                binding.tvRatingMain.text = nowPlaying.vote.toString()

                binding.cvItemMain.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, nowPlaying)
                        activity.startActivity(intent)
                    }
                }))
            }
        }
    }
}