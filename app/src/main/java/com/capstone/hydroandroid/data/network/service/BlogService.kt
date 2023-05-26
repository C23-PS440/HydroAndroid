package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.response.detail.DetailResponse
import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService {
    //GET ALL BLOG
    @GET("blog")
    suspend fun getAllBlog(): Response<HomeResponse>

    @GET("blog/{blogId}")
    suspend fun getDetailBlog(
        @Path("blogId") blogId : String
    ) : Response<DetailResponse>
}