package com.capstone.hydroandroid.ui.blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogUserAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentBlogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlogFragment : Fragment(R.layout.fragment_blog) {
    private val binding: FragmentBlogBinding by viewBinding()
    private val viewModel: BlogViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            fetchData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun fetchData() {
        viewModel.getAllUserBlog().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val blogUserAdapter = BlogUserAdapter(it.data.blog)
                    val layoutManager = GridLayoutManager(requireContext(),2,
                        GridLayoutManager.VERTICAL,false)
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = blogUserAdapter
                }
            }
        }
    }
}
