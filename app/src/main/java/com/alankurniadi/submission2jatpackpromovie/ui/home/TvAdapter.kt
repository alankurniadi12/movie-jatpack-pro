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
import com.alankurniadi.submission2jatpackpromovie.data.source.local.entity.NowAiringTv
import com.alankurniadi.submission2jatpackpromovie.databinding.ItemCardMainBinding
import com.alankurniadi.submission2jatpackpromovie.ui.CostumeOnItemClickListener
import com.alankurniadi.submission2jatpackpromovie.ui.detail.tv.DetailTvActivity
import com.bumptech.glide.Glide

class TvAdapter internal constructor(private val activity: Activity): PagedListAdapter<NowAiringTv, TvAdapter.TvViewHolder>(DIFF_CALLBACK) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val binding = ItemCardMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val list = getItem(position)
        if (list != null) {
            holder.bind(list)
        }
    }

    inner class TvViewHolder(private val binding: ItemCardMainBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(nowAiringTv: NowAiringTv) {
            with(itemView) {
                Glide.with(this)
                    .load(Url.POSTER_URL+nowAiringTv.poster_path)
                    .error(R.drawable.ic_error)
                    .into(binding.imgPosterMain)
                binding.tvTitleMain.text = nowAiringTv.name
                binding.tvAirDate.text = nowAiringTv.first_air_date

                binding.cvItemMain.setOnClickListener(CostumeOnItemClickListener(object : CostumeOnItemClickListener.OnItemClickCallback {
                    override fun onItemClicked(v: View) {
                        val intent = Intent(activity, DetailTvActivity::class.java)
                        intent.putExtra(DetailTvActivity.EXTRA_TV, nowAiringTv)
                        activity.startActivity(intent)
                    }
                }))
            }
        }
    }
}