package com.capstone.hydroandroid.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.data.network.response.register.RegisterResponse
import com.capstone.hydroandroid.source.register.RegisterRepository

class RegisterViewModel(private val repository: RegisterRepository): ViewModel() {
    fun register(registerRequest: RegisterRequest) : LiveData<EventResult<RegisterResponse>>{
        return repository.register(registerRequest)
    }
}