package com.capstone.hydroandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.data.network.response.login.LoginResponse
import com.capstone.hydroandroid.source.login.LoginRepository
import com.capstone.hydroandroid.storage.UserLoggedIn

class LoginViewModel(private val repository: LoginRepository): ViewModel() {
    fun login (loginRequest: LoginRequest) : LiveData<EventResult<LoginResponse>> {
        return repository.login(loginRequest)
    }
    fun setUserLoggedIn(userLoggedIn: UserLoggedIn) = repository.setUserLoggedIn(userLoggedIn)
}