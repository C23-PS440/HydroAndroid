package com.capstone.hydroandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView : BottomNavigationView =  binding.navView
        navView.background = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.addOnDestinationChangedListener{_,destination,_ ->
            when(destination.id){
                R.id.navigation_home -> showButtonNav()
                R.id.navigation_profile -> showButtonNav()
                R.id.navigation_blog -> {

                }
                R.id.place_holder -> navView.menu.getItem(2).isEnabled = false
                R.id.fab -> {
                    Toast.makeText(this,"Ini Camera" , Toast.LENGTH_SHORT).show()
                }
                else -> hideButtonNav()
            }
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_blog, R.id.navigation_profile
            )
        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    private fun showButtonNav() {
        binding.navView.visibility = View.VISIBLE

    }
    private fun hideButtonNav() {
        binding.navBar.visibility = View.GONE
        binding.fab.visibility = View.GONE
        binding.navView.visibility = View.GONE
    }
}