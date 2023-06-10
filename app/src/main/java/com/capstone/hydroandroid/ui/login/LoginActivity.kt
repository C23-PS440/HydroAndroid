package com.capstone.hydroandroid.ui.login

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        showUi()

        binding.loginButton.setOnClickListener {
            loginAction()
        }
        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity , RegisterActivity::class.java))
        }

    }

        private fun loginAction(){
        val data = LoginRequest(
            email = binding.email.text.toString(),
            password = binding.password.text.toString()
        )

        viewModel.login(data).observe(this) {

            val dialogLoading = Dialog(this)
            dialogLoading.setContentView(R.layout.loading_dialog)


            when (it) {
                is EventResult.Error -> {
                    dialogLoading.dismiss()
//                    hideUi()
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                    showUi()

                    dialogLoading.show()
                }
                is EventResult.Success -> {
                    dialogLoading.dismiss()
                    showUi()
                    val userLoggedIn = UserLoggedIn(
                        userId =   it.data.loginResult.userId,
                        name = it.data.loginResult.name,
                        token =  it.data.loginResult.token
                    )
                    viewModel.setUserLoggedIn(userLoggedIn)
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity , MainActivity::class.java))
                }
            }
        }
    }


    private fun showUi(){
        binding.loginView.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }
    private fun hideUi(){
        binding.loginView.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showUi()
    }
}