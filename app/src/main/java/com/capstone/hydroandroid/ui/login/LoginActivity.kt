package com.capstone.hydroandroid.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.databinding.ActivityLoginBinding
import com.capstone.hydroandroid.storage.UserLoggedIn
import com.capstone.hydroandroid.ui.MainActivity
import com.capstone.hydroandroid.ui.register.RegisterActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.loginButton.setOnClickListener {
            loginAction()
        }
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity , RegisterActivity::class.java))
        }

    }

        private fun loginAction(){
        val data = LoginRequest(
            email = "", password = "")

        viewModel.login(data).observe(this) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val userLoggedIn = UserLoggedIn(
                        username =   it.data.loginResult.username,
                        email =it.data.loginResult.email, accessToken =  it.data.loginResult.token

                    )
                    viewModel.setUserLoggedIn(userLoggedIn)
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity , MainActivity::class.java))
                }
            }
        }
    }

}