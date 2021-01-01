package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.submission2jatpackpromovie.databinding.FragmentTvBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory

class TvBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentTvBookmarkBinding
    private lateinit var adapter: BookmarkTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val vm = ViewModelProvider(this, factory)[BookmarkTvViewModel::class.java]
            adapter = BookmarkTvAdapter(requireActivity())
            binding.progressBarTv.visibility = View.VISIBLE
            vm.getBookmarkTv().observe(viewLifecycleOwner, Observer { data ->
                binding.progressBarTv.visibility = View.GONE
                adapter.submitList(data)
                adapter.notifyDataSetChanged()
            })
            binding.rvBookmarkTv.layoutManager = LinearLayoutManager(context)
            binding.rvBookmarkTv.setHasFixedSize(true)
            binding.rvBookmarkTv.adapter = adapter
        }
    }
}