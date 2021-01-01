package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv

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
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemListBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.tv.DetailTvActivity
import com.bumptech.glide.Glide

class BookmarkTvAdapter internal constructor(private val activity: Activity): PagedListAdapter<NowAiringTv, BookmarkTvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NowAiringTv>() {
            override fun areItemsTheSame(oldItem: NowAiringTv, newItem: NowAiringTv): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NowAiringTv, newItem: NowAiringTv): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemListBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }
    }

    inner class ViewHolder(private val binding: ItemListBookmarkBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NowAiringTv) {
            with(itemView) {
                Glide.with(this)
                    .load(Url.POSTER_URL +data.poster_path)
                    .error(R.drawable.ic_error)
                    .into(binding.imgListBookmark)
                binding.titleBookmark.text = data.name

                binding.cvBookmark.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, DetailTvActivity::class.java)
                        intent.putExtra(DetailTvActivity.EXTRA_BOOKMARK_TV, data)
                        activity.startActivity(intent)
                    }

                }))
            }
        }
    }
}