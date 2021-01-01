package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.trending

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.submission2jatpackpromovie.databinding.FragmentBookmarkTrendingBinding
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory

class BookmarkTrendingFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkTrendingBinding
    private lateinit var adapter: BookmarkTrendingAdapter
    private lateinit var vm: BookmarkTrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        vm = ViewModelProvider(this, factory)[BookmarkTrendingViewModel::class.java]
        adapter = BookmarkTrendingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkTrendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity != null) {
            binding.progressBarTrending.visibility = View.VISIBLE
            vm.getBookmarkTrending().observe(viewLifecycleOwner, Observer { data ->
                binding.progressBarTrending.visibility = View.GONE
                adapter.submitList(data)
                binding.rvBookmarkTrending.layoutManager = LinearLayoutManager(context)
                binding.rvBookmarkTrending.setHasFixedSize(true)
                binding.rvBookmarkTrending.adapter = adapter
            })
        }
    }

}