package com.capstone.hydroandroid.source.login

import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.data.network.service.AuthService

class LoginRemoteDataSource constructor(private val authService: AuthService) {

    suspend fun login(loginRequest: LoginRequest) = authService.login(loginRequest)

}