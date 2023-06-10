package com.capstone.hydroandroid.ui.custom

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.capstone.hydroandroid.R
import com.capstone.hydroandroid.databinding.FragmentDialogFailedRegisterBinding
import com.capstone.hydroandroid.ui.login.LoginActivity

class DialogFailedRegisterFragment : DialogFragment(R.layout.fragment_dialog_failed_register) {
    private val binding: FragmentDialogFailedRegisterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvOk.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            dismiss()
            startActivity(intent)
        }
    }
}