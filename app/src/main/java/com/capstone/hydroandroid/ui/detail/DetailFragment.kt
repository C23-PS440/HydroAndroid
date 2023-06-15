package com.capstone.hydroandroid.ui.detail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val argument: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //MINTOL RAPIKAN DIALOG
        val dialogLoading = Dialog(requireContext())
        dialogLoading.setContentView(R.layout.fragment_dialog_failed_register)

        viewModel.getDetailBlog(argument.blogId).observe(viewLifecycleOwner){
            when (it) {
                is EventResult.Error -> {
                    dialogLoading.dismiss()
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                    dialogLoading.show()
                }
                is EventResult.Success -> {
                    dialogLoading.dismiss()
                    Glide.with(requireContext())
                        .load(it.data.response.imageUrl)
                        .into(binding.imgBlog)
                    binding.tvNameUser.text = it.data.response.createdBy
                    binding.tvBlogTitle.text = it.data.response.blogTitle
                    binding.tvDetailBlog.text = it.data.response.blogDescription
                    binding.tvDateCreated.text = it.data.response.dateCreated
                }
            }
        }
    }
}