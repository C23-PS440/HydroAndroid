package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.response.detail.DetailResponse
import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import com.capstone.hydroandroid.data.network.response.search.SearchResponse
import com.capstone.hydroandroid.data.network.response.video.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogService {
    //GET ALL BLOG
    @GET("blog")
    suspend fun getAllBlog(): Response<HomeResponse>

    @GET("blog/{blogId}")
    suspend fun getDetailBlog(
        @Path("blogId") blogId : String
    ) : Response<DetailResponse>

    @GET("search/blog")
    suspend fun getSearchedBlog(
        @Query("query") query : String
    ) : Response<SearchResponse>


    @GET("video")
    suspend fun getAllVideo():Response<VideoResponse>



}