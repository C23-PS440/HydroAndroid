package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.data.network.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    //REGISTER
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ) : Response<RegisterResponse>

    //LOGIN

}