package com.capstone.hydroandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.VideoView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogAdapter
import com.capstone.hydroandroid.adapter.VideoAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentHomeBinding
import com.capstone.hydroandroid.ui.video.VideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val blogViewModel: HomeViewModel by viewModel()
    private val videoViewModel : VideoViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.search -> {
                    findNavController().navigate(R.id.action_navigation_home_to_searchFragment)
                    true
                }
                else -> false
            }
        }
        fetchVideo()
        fetchBlog()
    }


    private fun fetchBlog(){
        blogViewModel.getAllBlog().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    //BLOG
                    val blogAdapter = BlogAdapter(it.data.blog)
                    val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = blogAdapter
                }
            }
        }
    }
    private fun fetchVideo(){
        videoViewModel.getAllVideo().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    //VIDEO
                    val videoAdapter = VideoAdapter(it.data.video)
                    val layoutManager2 = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    binding.rvVideo.layoutManager = layoutManager2
                    binding.rvVideo.adapter = videoAdapter
                }
            }
        }
    }


}