package com.capstone.hydroandroid.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.ui.MainActivity
import com.capstone.hydroandroid.ui.login.LoginActivity
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.log

class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        startActivity(Intent(this@SplashActivity, MainActivity::class.java))

        lifecycleScope.launch {
            viewModel.isLogin.collect{
                Log.d("error apa nih", it.toString())
                if (it){
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                }
                else {
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                }
            }
        }
    }
}