package com.capstone.hydroandroid.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.ActivityMainBinding
import com.capstone.hydroandroid.ui.blog.addblog.AddBlogFragment
import com.capstone.hydroandroid.ui.camera.CameraActivityDeteksi
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    private fun setUpBottomNav(){
        val navView: BottomNavigationView = binding.navView
        navView.background = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    setUpIconCamera()
                    binding.fab.setOnClickListener {
                        startActivity(Intent(this@MainActivity,CameraActivityDeteksi::class.java))
                    }
                    showButtonNav()
                }
                R.id.navigation_profile -> {
                    setUpIconCamera()
                    binding.fab.setOnClickListener {
                        startActivity(Intent(this@MainActivity,CameraActivityDeteksi::class.java))
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
                        startActivity(Intent(this@MainActivity,CameraActivityDeteksi::class.java))
                    }
                    showButtonNav()
                }
                R.id.place_holder -> navView.menu.getItem(2).isEnabled = false
                R.id.fab -> {
                    startActivity(Intent(this@MainActivity,CameraActivityDeteksi::class.java))
                }
                R.id.navigation_forum_diskusi ->{
                    showButtonNav()
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