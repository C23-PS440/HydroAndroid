package com.capstone.hydroandroid.source.register

import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.data.network.service.AuthService

class RegisterRemoteDataSource constructor(private val authService:AuthService) {
    suspend fun register(registerRequest: RegisterRequest) = authService.register(registerRequest)
}