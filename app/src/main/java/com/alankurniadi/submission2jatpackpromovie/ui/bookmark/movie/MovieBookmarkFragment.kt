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
    private lateinit var vm: BookmarkMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        vm = ViewModelProvider(this, factory)[BookmarkMovieViewModel::class.java]
        adapter = BookmarkMovieAdapter(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity != null) {
            binding.progressBarMovie.visibility = View.VISIBLE
            vm.getBookmark().observe(viewLifecycleOwner, Observer { data ->
                binding.progressBarMovie.visibility = View.GONE
                adapter.submitList(data)
                binding.rvBookmarkMovie.layoutManager = LinearLayoutManager(context)
                binding.rvBookmarkMovie.setHasFixedSize(true)
                binding.rvBookmarkMovie.adapter = adapter
            })

        }
    }
}