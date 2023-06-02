package com.capstone.hydroandroid.source.profile

import com.capstone.hydroandroid.data.network.service.AuthService

class ProfileRemoteDataSource constructor(private val authService: AuthService) {
    suspend fun getProfile() = authService.profile()
}