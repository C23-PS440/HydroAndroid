package com.capstone.hydroandroid.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.databinding.ActivityLoginBinding
import com.capstone.hydroandroid.databinding.ActivityRegisterBinding
import com.capstone.hydroandroid.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.loginBtn.setOnClickListener {
            registerAction()
        }
    }

    private fun registerAction(){
        val data = RegisterRequest(
            email = "", password = "", name = ""
        )

        viewModel.register(data).observe(this) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                }
            }
        }
    }
}