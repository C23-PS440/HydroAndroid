package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.data.network.response.login.LoginResponse
import com.capstone.hydroandroid.data.network.response.profile.ProfileResponse
import com.capstone.hydroandroid.data.network.response.register.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    //REGISTER
    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ) : Response<RegisterResponse>

    //LOGIN
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : Response<LoginResponse>

    //PROFILE
    @GET("user")
    suspend fun profile(
    ) : Response<ProfileResponse>

}