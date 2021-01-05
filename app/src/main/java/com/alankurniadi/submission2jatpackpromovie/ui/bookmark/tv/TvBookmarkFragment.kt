package com.alankurniadi.submission2jatpackpromovie.ui.bookmark.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.submission2jatpackpromovie.databinding.FragmentTvBookmarkBinding
import com.alankurniadi.submission2jatpackpromovie.viewmodel.ViewModelFactory

class TvBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentTvBookmarkBinding
    private lateinit var adapter: BookmarkTvAdapter
    private lateinit var vm: BookmarkTvViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        vm = ViewModelProvider(this, factory)[BookmarkTvViewModel::class.java]
        adapter = BookmarkTvAdapter(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (activity != null) {
            binding.progressBarTv.visibility = View.VISIBLE
            vm.getBookmarkTv().observe(viewLifecycleOwner, { data ->
                binding.progressBarTv.visibility = View.GONE
                adapter.submitList(data)
                binding.rvBookmarkTv.layoutManager = LinearLayoutManager(context)
                binding.rvBookmarkTv.setHasFixedSize(true)
                binding.rvBookmarkTv.adapter = adapter
            })

        }
    }
}