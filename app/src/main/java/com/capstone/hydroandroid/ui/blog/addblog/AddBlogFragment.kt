package com.capstone.hydroandroid.ui.blog.addblog

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.FragmentAddBlogBinding
import com.capstone.hydroandroid.databinding.FragmentHomeBinding
import com.capstone.hydroandroid.rotateBitmap
import com.capstone.hydroandroid.ui.MainActivity.Companion.CAMERA_X_RESULT
import com.capstone.hydroandroid.ui.camera.CameraActivity
import java.io.File


class AddBlogFragment : DialogFragment(R.layout.fragment_add_blog) {
    private val binding: FragmentAddBlogBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addPhotoButton.setOnClickListener {
            val intent = Intent(requireActivity(), CameraActivity::class.java)
            launcherIntentCameraX.launch(intent)
        }

    binding.btnPost.setOnClickListener {
        val tittle = binding.edtTitle.text.toString()
        val descrption = binding.edtDesc.text.toString()
        Toast.makeText(requireContext(), tittle,Toast.LENGTH_SHORT).show()
    }
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            binding.imgInputImgBlog.setImageBitmap(result)
        }
    }
}