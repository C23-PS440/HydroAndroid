package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import retrofit2.Response
import retrofit2.http.GET

interface BlogService {
    //GET ALL BLOG
    @GET("blog")
    suspend fun getAllBlog(): Response<HomeResponse>
}