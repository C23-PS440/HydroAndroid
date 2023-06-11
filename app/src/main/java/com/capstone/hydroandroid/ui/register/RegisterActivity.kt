package com.capstone.hydroandroid.ui.register

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.databinding.ActivityRegisterBinding
import com.capstone.hydroandroid.ui.custom.DialogFailedRegisterFragment
import com.capstone.hydroandroid.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

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
}