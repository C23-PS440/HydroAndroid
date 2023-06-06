package com.capstone.hydroandroid.ui.blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogAdapter
import com.capstone.hydroandroid.adapter.BlogUserAdapter
import com.capstone.hydroandroid.adapter.VideoAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.home.Blog
import com.capstone.hydroandroid.databinding.FragmentBlogBinding
import com.capstone.hydroandroid.databinding.FragmentProfileBinding
import com.capstone.hydroandroid.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BlogFragment : Fragment(R.layout.fragment_blog) {
    private val binding: FragmentBlogBinding by viewBinding()
    private val viewModel: BlogViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()

    }
    private fun fetchData(){
        viewModel.getAllUserBlog().observe(viewLifecycleOwner){
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    //VIDEO
                    val videoAdapter = BlogUserAdapter(it.data.blog as List<Blog>)
                    val layoutManager2 = LinearLayoutManager(requireContext(),
                        LinearLayoutManager.VERTICAL,false)
                    binding.rvVideo.layoutManager = layoutManager2
                    binding.rvVideo.adapter = videoAdapter
                }
            }
        }
    }
}