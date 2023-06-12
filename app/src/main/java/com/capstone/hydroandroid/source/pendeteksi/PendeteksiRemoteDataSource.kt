package com.capstone.hydroandroid.source.pendeteksi

import com.capstone.hydroandroid.data.network.service.BlogService
import okhttp3.MultipartBody

class PendeteksiRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun pendeteksi(
        file:MultipartBody.Part
    ) = blogService.pendeteksiBlog(file)

}