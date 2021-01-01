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
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemListSimpleBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.movie.DetailMovieActivity
import com.alankurniadi.submission2jatpackpromovie.ui.detail.tv.DetailTvActivity
import com.bumptech.glide.Glide

class TrendingAdapter internal constructor(private val activity: Activity): PagedListAdapter<TrendingWeek, TrendingAdapter.WeekViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TrendingWeek>() {
            override fun areItemsTheSame(oldItem: TrendingWeek, newItem: TrendingWeek): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TrendingWeek, newItem: TrendingWeek): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val binding = ItemListSimpleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeekViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }
    }

    inner class WeekViewHolder(private val binding: ItemListSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingWeek: TrendingWeek) {
            with(itemView) {
                Glide.with(this)
                    .load(Url.POSTER_URL+trendingWeek.poster_path)
                    .error(R.drawable.ic_error)
                    .into(binding.imgItemCircle)

                binding.imgItemCircle.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        when(trendingWeek.media_type) {
                            "movie" -> {
                                val intentMovie = Intent(activity, DetailMovieActivity::class.java)
                                intentMovie.putExtra(DetailMovieActivity.FROM_TRENDING, trendingWeek)
                                activity.startActivity(intentMovie)
                            }
                            "tv" -> {
                                val intentTv = Intent(activity, DetailTvActivity::class.java)
                                intentTv.putExtra(DetailTvActivity.FROM_TRENDING, trendingWeek)
                                activity.startActivity(intentTv)
                            }
                        }
                    }
                }))
            }
        }
    }
}