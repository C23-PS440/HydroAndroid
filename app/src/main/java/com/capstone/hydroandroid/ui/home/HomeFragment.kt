package com.capstone.hydroandroid.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.BlogAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentHomeBinding
import com.capstone.hydroandroid.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

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
        viewModel.getAllBlog().observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val blogAdapter = BlogAdapter(it.data.blog)
                    val layoutManager = GridLayoutManager(requireContext(),2)
                    binding.rvBlog.layoutManager = layoutManager
                    binding.rvBlog.adapter = blogAdapter
//                    val username = viewModel.getUsername()
//                    binding.usernameTextView.text = "Selamat Pagi $username"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}