package com.alankurniadi.submission2jatpackpromovie.ui

import android.view.View

class CostumeOnItemClickListener(private val onItemClickCallback: OnItemClickCallback): View.OnClickListener {

    override fun onClick(v: View) {
        onItemClickCallback.onItemClicked(v)
    }

    interface OnItemClickCallback {
        fun onItemClicked(v: View)
    }

}