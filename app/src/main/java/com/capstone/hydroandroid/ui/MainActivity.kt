package com.capstone.hydroandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.ActivityMainBinding
import com.capstone.hydroandroid.ui.camera.CameraActivity
import com.capstone.hydroandroid.ui.camera.CameraFragment
import com.capstone.hydroandroid.ui.home.HomeFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.background = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Home",Toast.LENGTH_SHORT).show()
                    }
                    showButtonNav()
                }
                R.id.navigation_profile -> {
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Profile",Toast.LENGTH_SHORT).show()
//                        navController.navigate(R.id.)

                        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                        val navController2 = navHostFragment.navController
                        navController2.navigate()


                    }
                    showButtonNav()
                }
                R.id.navigation_blog -> {
                    binding.fab.setOnClickListener {
                        Toast.makeText(this, "Blog",Toast.LENGTH_SHORT).show()
                    }
                    showButtonNav()
                }
                R.id.place_holder -> navView.menu.getItem(2).isEnabled = false
                R.id.fab -> {
                    Toast.makeText(this, "Ini Camera", Toast.LENGTH_SHORT).show()
                }
                else -> hideButtonNav()
            }
        }
        navView.setupWithNavController(navController)
//        binding.fab.setOnClickListener {
//            startActivity(Intent(this@MainActivity,CameraActivity::class.java))
//        }
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
}