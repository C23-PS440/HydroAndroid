package com.capstone.hydroandroid.ui.register

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.databinding.ActivityRegisterBinding
import com.capstone.hydroandroid.ui.custom.CustomInputPassword
import com.capstone.hydroandroid.ui.custom.DialogFailedRegisterFragment
import com.capstone.hydroandroid.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.getScopeName

class RegisterActivity : AppCompatActivity(){
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.registerButton.setOnClickListener {
            registerAction()
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }

        binding.name.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_ENTER
        }
        binding.email.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_ENTER
        }

        binding.password.setOnKeyListener { _, keyCode, _ ->
            keyCode == KeyEvent.KEYCODE_ENTER
        }
        binding.name.doOnTextChanged { _, _, _, _ ->
            setCustomButtonEnable()
        }
        binding.email.doOnTextChanged { _, _, _, _ ->
            setCustomButtonEnable()
        }
        binding.password.doOnTextChanged { _, _, _, _ ->
            setCustomButtonEnable()
        }

        setCustomButtonEnable()
    }

    private fun registerAction(){
        val data = RegisterRequest(
            email = binding.email.text.toString(),
            password = binding.password.text.toString(),
            fullName = binding.name.text.toString()
        )

        viewModel.register(data).observe(this) {

            val dialogLoading = Dialog(this)
            dialogLoading.setContentView(R.layout.fragment_dialog_failed_register)

            when (it) {
                is EventResult.Error -> {
//                    dialogLoading.show()
                    DialogFailedRegisterFragment().show(supportFragmentManager,"dialog")
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                    dialogLoading.dismiss()
                }
                is EventResult.Success -> {
                    dialogLoading.dismiss()
                    Toast.makeText(this, it.data.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
                }
            }
        }
    }

    private fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun setCustomButtonEnable() {
        val emailEdt = binding.email.text.toString().trim()
        val emailValidator = emailEdt.isValidEmail() && emailEdt.isNotEmpty()
        val nameEdt = binding.name.text.toString().trim()
        val nameValidator = nameEdt.isNotEmpty()
        val passwordEdt = binding.password.text.toString().trim()
        val passwordValidator = passwordEdt.length >= CustomInputPassword.MIN_PASSWORD && passwordEdt.isNotEmpty()

        binding.registerButton.isEnabled = emailValidator && nameValidator && passwordValidator
    }
}