package com.capstone.hydroandroid.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import android.widget.VideoView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogAdapter
import com.capstone.hydroandroid.adapter.SearchAdapter
import com.capstone.hydroandroid.adapter.VideoAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentHomeBinding
import com.capstone.hydroandroid.ui.profile.ProfileViewModel
import com.capstone.hydroandroid.ui.search.SearchViewModel
import com.capstone.hydroandroid.ui.video.VideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()
    private val blogViewModel: HomeViewModel by viewModel()
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchBlog()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("cekQuery",query.toString())
                binding.searchView.clearFocus()
                if (query != null) {
                    fetchDataSearch(query)
                } else {
                    fetchBlog()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == ""){
                    Log.d("cekQueryCHANGE","asdasds")
                    fetchBlog()
                }

                return true
            }
        }
        )
        fetchUsername()
    }

    private fun fetchBlog() {
        blogViewModel.getAllBlog().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    //BLOG
                    val blogAdapter = BlogAdapter(it.data.blogs)
                    val layoutManager = GridLayoutManager(
                        requireContext(), 2,
                        GridLayoutManager.VERTICAL, false
                    )
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = blogAdapter
                }
            }
        }
    }

    private fun fetchDataSearch(query: String) {
        searchViewModel.getSearchedBlog(query).observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val adapter = SearchAdapter(it.data.response)
                    val layoutManager = GridLayoutManager(
                        requireContext(), 2,
                        GridLayoutManager.VERTICAL, false
                    )
                    binding.rvBlog.setHasFixedSize(true)
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = adapter
                }
            }
        }
    }

    private fun fetchUsername() {
        val username = blogViewModel.getUsername()
        binding.usernameTextView.text = "Selamat Pagi $username"
    }
}