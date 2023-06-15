package com.capstone.hydroandroid.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.capstone.hydroandroid.databinding.ActivitySplashBinding
import com.capstone.hydroandroid.ui.MainActivity
import com.capstone.hydroandroid.ui.login.LoginActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()
    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            binding.progressBar.visibility = View.VISIBLE
            lifecycleScope.launch {
                viewModel.isLogin.collect{
                    if (it){
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                    else {
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }
                    binding.progressBar.visibility = View.GONE
                }
            }
        }, 3000)
    }
}