package com.capstone.hydroandroid.ui.camera

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.capstone.hydroandroid.*
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.databinding.ActivityCameraBinding
import com.capstone.hydroandroid.databinding.ActivityCameraDeteksiBinding
import com.capstone.hydroandroid.ui.MainActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class CameraActivityDeteksi : AppCompatActivity() {
    private lateinit var binding:ActivityCameraDeteksiBinding
    private var getFile: File? = null

    private val viewModel: PendeteksiViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraDeteksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgInputImgBlog.setOnClickListener {
            chooseImageDialog()
        }
        binding.btnPost.setOnClickListener {
            uploadBlog()
        }


    }
    private fun chooseImageDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> startGallery() }
            .setNegativeButton("Camera") { _, _ -> startCameraX() }
            .show()
    }
    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == MainActivity.CAMERA_X_RESULT) {
            val myFile = result.data?.getSerializableExtra("picture") as File
            val isBackCamera = result.data?.getBooleanExtra("isBackCamera", true) as Boolean
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
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this)
            getFile = myFile
            binding.imgInputImgBlog.setImageURI(selectedImg)
        }
    }
    private fun uploadBlog() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )

            val dialogLoading = Dialog(this)
            dialogLoading.setContentView(R.layout.loading_dialog)

            viewModel.pendeteksi(imageMultipart).observe(this) {
                when (it) {

                    is EventResult.Error -> {
                        dialogLoading.dismiss()
                        Toast.makeText(
                            this,
                            "Lengkapi Data WEKWKKWEKEW",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is EventResult.Loading -> {
                        dialogLoading.show()
                    }

                    is EventResult.Success -> {
                        dialogLoading.dismiss()
                        binding.diseaseName.text = it.data.response.diseaseName
                        binding.plantName.text = it.data.response.plantName
                        binding.diseaseRecognition.text = it.data.response.diseaseRecognition
                    }

                }
            }
        }
    }
}