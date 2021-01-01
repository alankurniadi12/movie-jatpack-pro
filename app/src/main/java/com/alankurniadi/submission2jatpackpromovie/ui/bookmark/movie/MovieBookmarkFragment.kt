package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.submission2jatpackpromovie.databinding.FragmentMovieBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory


class MovieBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentMovieBookmarkBinding
    private lateinit var adapter: BookmarkMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val vm = ViewModelProvider(this, factory)[BookmarkMovieViewModel::class.java]
            adapter = BookmarkMovieAdapter(requireActivity())
            binding.progressBarMovie.visibility = View.VISIBLE
            vm.getBookmark().observe(viewLifecycleOwner, Observer { data ->
                binding.progressBarMovie.visibility = View.GONE
                adapter.submitList(data)
                adapter.notifyDataSetChanged()
            })
            binding.rvBookmarkMovie.layoutManager = LinearLayoutManager(context)
            binding.rvBookmarkMovie.setHasFixedSize(true)
            binding.rvBookmarkMovie.adapter = adapter
        }
    }
}