package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.trending

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.api.Url.POSTER_URL
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.TrendingWeek
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemListBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.movie.DetailMovieActivity
import com.alankurniadi.submission2jatpackpromovie.ui.detail.tv.DetailTvActivity
import com.bumptech.glide.Glide

class BookmarkTrendingAdapter internal constructor(): PagedListAdapter<TrendingWeek, BookmarkTrendingAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view = ItemListBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }

    }

    inner class BookmarkViewHolder(private val binding: ItemListBookmarkBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TrendingWeek) {
            Log.e("TrendingAdapter", "DATA: $data")
            with(itemView) {
                Glide.with(context)
                    .load(POSTER_URL+data.poster_path)
                    .error(R.drawable.ic_error)
                    .into(binding.imgListBookmark)
                binding.tvTypeBookmark.visibility = View.VISIBLE
                if (data.title != null) {
                    binding.titleBookmark.text = data.title
                    binding.tvTypeBookmark.text = data.media_type
                } else {
                    binding.titleBookmark.text = data.name
                    binding.tvTypeBookmark.text = data.media_type
                }

                binding.cvBookmark.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        when(data.media_type) {
                            "movie" -> {
                                val intent = Intent(context, DetailMovieActivity::class.java)
                                intent.putExtra(DetailMovieActivity.FROM_TRENDING, data)
                                context.startActivity(intent)
                            }
                            "tv" -> {
                                val intent = Intent(context, DetailTvActivity::class.java)
                                intent.putExtra(DetailTvActivity.FROM_TRENDING, data)
                                context.startActivity(intent)
                            }
                        }
                    }
                }))
            }
        }
    }
}