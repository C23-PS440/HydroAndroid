package com.capstone.hydroandroid.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.ActivityMainBinding
import com.capstone.hydroandroid.rotateBitmap
import com.capstone.hydroandroid.ui.blog.addblog.AddBlogFragment
import com.capstone.hydroandroid.ui.camera.CameraActivity
import com.capstone.hydroandroid.ui.camera.CameraFragment
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBottomNav()

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
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

//            binding.previewImageView.setImageBitmap(result)
        }
    }

    private fun setUpBottomNav(){
        val navView: BottomNavigationView = binding.navView
        navView.background = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    setUpIconCamera()
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,CameraActivity::class.java))
                    }
                    showButtonNav()
                }
                R.id.navigation_profile -> {
                    setUpIconCamera()
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Profile",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,CameraActivity::class.java))
                    }
                    showButtonNav()
                }
                R.id.navigation_blog -> {
                    setUpIconAdd()
                    binding.fab.setOnClickListener {
                        AddBlogFragment().show(supportFragmentManager,"dialog")
                    }
                    showButtonNav()
                }
                R.id.navigation_search -> {
                    setUpIconCamera()
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Profile",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity,CameraActivity::class.java))
                    }
                    showButtonNav()
                }
                R.id.place_holder -> navView.menu.getItem(2).isEnabled = false
                R.id.fab -> {
                    Toast.makeText(this, "Ini Camera", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@MainActivity,CameraActivity::class.java))
                }
                else -> hideButtonNav()
            }
        }
        navView.setupWithNavController(navController)
    }
    private fun showButtonNav() {
        binding.navView.visibility = View.VISIBLE
        binding.navBar.visibility = View.VISIBLE
        binding.fab.visibility = View.VISIBLE
    }
    private fun hideButtonNav() {
        binding.navBar.visibility = View.GONE
        binding.fab.visibility = View.GONE
        binding.navView.visibility = View.GONE
    }
    private fun setUpIconAdd(){
        binding.fab.setImageResource(R.drawable.plus)
    }
    private fun setUpIconCamera(){
        binding.fab.setImageResource(R.drawable.camera)
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}