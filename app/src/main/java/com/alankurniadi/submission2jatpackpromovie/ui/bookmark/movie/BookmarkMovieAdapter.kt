package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.movie

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alankurniadi.submission2jatpackpromovie.R
import com.alankurniadi.submission2jatpackpromovie.api.Url.POSTER_URL
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowPlayingMovie
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemListBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.movie.DetailMovieActivity
import com.bumptech.glide.Glide

class BookmarkMovieAdapter internal constructor(private val activity: Activity):PagedListAdapter<NowPlayingMovie, BookmarkMovieAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

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
        fun bind(data: NowPlayingMovie) {
            with(itemView) {
                Glide.with(this)
                    .load(POSTER_URL+data.poster)
                    .error(R.drawable.ic_error)
                    .into(binding.imgListBookmark)
                binding.titleBookmark.text = data.title

                binding.cvBookmark.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_BOOKMARK, data)
                        activity.startActivity(intent)
                    }

                }))
            }
        }

    }
}