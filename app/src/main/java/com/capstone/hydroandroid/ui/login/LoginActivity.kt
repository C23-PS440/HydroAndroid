package com.capstone.hydroandroid.ui.login

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.databinding.ActivityLoginBinding
import com.capstone.hydroandroid.storage.UserLoggedIn
import com.capstone.hydroandroid.ui.MainActivity
import com.capstone.hydroandroid.ui.custom.CustomInputPassword
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
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.email.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_ENTER
        }

        binding.password.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_ENTER
        }

        binding.email.doOnTextChanged { _, _, _, _ ->
            setCustomButtonEnable()
        }

        binding.password.doOnTextChanged { _, _, _, _ ->
            setCustomButtonEnable()
        }

        setCustomButtonEnable()

    }

    private fun loginAction() {
        val data = LoginRequest(
            email = binding.email.text.toString(),
            password = binding.password.text.toString()
        )
        val dialogLoading = Dialog(this)
        dialogLoading.setContentView(R.layout.fragment_dialog_failed_register)


        viewModel.login(data).observe(this) {
            when (it) {
                is EventResult.Error -> {
                    dialogLoading.dismiss()
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                    showUi()
                    dialogLoading.show()
                }
                is EventResult.Success -> {
                    Toast.makeText(this, "LESGOOO", Toast.LENGTH_SHORT).show()
                    dialogLoading.dismiss()
                    showUi()
                    val userLoggedIn = UserLoggedIn(
                        userId = it.data.loginResult.userId,
                        name = it.data.loginResult.name,
                        token = it.data.loginResult.token,
                        email = it.data.loginResult.email
                    )
                    viewModel.setUserLoggedIn(userLoggedIn)
                    startActivity(Intent(this , MainActivity::class.java))
                }
            }
        }
    }

    private fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun setCustomButtonEnable() {
        val emailEdt = binding.email.text.toString().trim()
        val emailValidator = emailEdt.isValidEmail() && emailEdt.isNotEmpty()
        val passwordEdt = binding.password.text.toString().trim()
        val passwordValidator = passwordEdt.length >= CustomInputPassword.MIN_PASSWORD && passwordEdt.isNotEmpty()

        binding.loginButton.isEnabled = emailValidator && passwordValidator
    }

    private fun showUi() {
        binding.loginView.visibility = View.VISIBLE
        binding.errorView.visibility = View.GONE
    }

    private fun hideUi() {
        binding.loginView.visibility = View.GONE
        binding.errorView.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showUi()
    }
}