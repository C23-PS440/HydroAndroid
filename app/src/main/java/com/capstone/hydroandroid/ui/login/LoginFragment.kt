package com.capstone.hydroandroid.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.databinding.FragmentLoginBinding
import com.capstone.hydroandroid.storage.UserLoggedIn
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginBtn.setOnClickListener {
            loginAction()
        }
    }
    private fun loginAction(){
        val data = LoginRequest(
            email = "", password = "")

        viewModel.login(data).observe(viewLifecycleOwner) {
            when (it) {
                is EventResult.Error -> {
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                is EventResult.Loading -> {
                }
                is EventResult.Success -> {
                    val userLoggedIn = UserLoggedIn(
                        username =   it.data.loginResult.username,
                        email =it.data.loginResult.email, accessToken =  it.data.loginResult.token

                    )


                    viewModel.setUserLoggedIn(userLoggedIn)
                    Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}