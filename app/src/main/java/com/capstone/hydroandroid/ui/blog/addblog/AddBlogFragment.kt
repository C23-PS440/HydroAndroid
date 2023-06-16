package com.capstone.hydroandroid.ui.blog.addblog

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.FragmentAddBlogBinding
import com.capstone.hydroandroid.reduceFileImage
import com.capstone.hydroandroid.ui.MainActivity.Companion.CAMERA_X_RESULT
import com.capstone.hydroandroid.ui.blog.BlogViewModel
import com.capstone.hydroandroid.ui.camera.CameraActivity
import com.capstone.hydroandroid.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class AddBlogFragment : DialogFragment(R.layout.fragment_add_blog) {

    private val binding: FragmentAddBlogBinding by viewBinding()
    private val viewModel: BlogViewModel by viewModel()
    private var getFile: File? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addPhotoButton.setOnClickListener {
            chooseImageDialog()
        }
        binding.btnPost.setOnClickListener {
            uploadBlog()
        }
    }
    private fun chooseImageDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> startGallery() }
            .setNegativeButton("Camera") { _, _ -> startCameraX() }
            .show()
    }
    private fun startCameraX() {
        val intent = Intent(requireActivity(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }
    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireContext())
            getFile = myFile
            binding.imgInputImgBlog.setImageURI(selectedImg)
        }
    }
    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            getFile = myFile

            val rotation = if (isBackCamera) 0 else 180

            val resultBitmap = BitmapFactory.decodeFile(myFile.path)
            val rotatedBitmap = rotateBitmap(resultBitmap, rotation)

            binding.imgInputImgBlog.setImageBitmap(rotatedBitmap)
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, rotationDegrees: Int): Bitmap {
        val matrix = Matrix().apply { postRotate(rotationDegrees.toFloat()) }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun uploadBlog() {
        Toast.makeText(requireContext(), "Lengkapi Data", Toast.LENGTH_SHORT).show()
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val tittle = binding.edtTitle.text.toString().toRequestBody("text/plain".toMediaType())
            val description =  binding.edtDesc.text.toString().toRequestBody("text/plain".toMediaType())
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val currentTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
            val formattedDateTime = currentTime.format(formatter)
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                "$formattedDateTime${file.name}",
                requestImageFile
            )
            viewModel.uploadBlog(imageMultipart,tittle,description).observe(viewLifecycleOwner){
                when (it) {
                    is EventResult.Error -> {
                        Toast.makeText(
                            requireContext(),
                            "Lengkapi Data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is EventResult.Loading -> {
                    }
                    is EventResult.Success -> {
                        Toast.makeText(context,it.data.message,Toast.LENGTH_SHORT).show()
                        dismiss()
                    }
                }
            }
        }
    }
}