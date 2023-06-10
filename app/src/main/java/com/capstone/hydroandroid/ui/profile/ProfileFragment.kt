package com.capstone.hydroandroid.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.adapter.ProfileBlogAdapter
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentProfileBinding
import com.capstone.hydroandroid.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchProfile()

        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(activity, LoginActivity::class.java))
        }

//        viewModel.getProfile().observe(viewLifecycleOwner){
//            when (it) {
//                is EventResult.Error -> {
//                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
//                }
//                is EventResult.Loading -> {
//                }
//                is EventResult.Success -> {
//                    binding.name.text = it.data.username
//                    binding.email.text = it.data.email
//                    val blogAdapter = ProfileBlogAdapter(it.data.userBlog)
//                    val layoutManager = LinearLayoutManager(requireContext(),
//                        LinearLayoutManager.HORIZONTAL,false)
//                    binding.rvBlog.layoutManager = layoutManager
//                    binding.rvBlog.adapter = blogAdapter
//                }
//            }
//        }
    }

    private fun fetchProfile() {
        val username = viewModel.getUsername()
        val email = viewModel.getEmail()
        binding.name.text = username.toString()
        binding.email.text = email.toString()
    }
}