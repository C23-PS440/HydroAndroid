package com.capstone.hydroandroid.data.network.service

import com.capstone.hydroandroid.data.network.response.addBlog.AddBlogResponse
import com.capstone.hydroandroid.data.network.response.blog.UserBlogResponse
import com.capstone.hydroandroid.data.network.response.detail.DetailResponse
import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import com.capstone.hydroandroid.data.network.response.pendeteksi.PredictResponse
import com.capstone.hydroandroid.data.network.response.search.SearchResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface BlogService {
    //GET ALL BLOG
    @GET("blogs")
    suspend fun getAllBlog(): Response<HomeResponse>

    @GET("blogs/{blogId}")
    suspend fun getDetailBlog(
        @Path("blogId") blogId : String
    ) : Response<DetailResponse>

    @GET("blogsWithName")
    suspend fun getSearchedBlog(
        @Query("blogTitle") query : String
    ) : Response<SearchResponse>

    @GET("userPrivateBlogs")
    suspend fun getAllUserBlog() : Response<UserBlogResponse>

    @Multipart
    @POST("blogs")
    suspend fun uploadBlog(
        @Part file : MultipartBody.Part,
        @Part("blogTitle") blogTitle : RequestBody,
        @Part("blogDescription") blogDescription : RequestBody
    ) : Response<AddBlogResponse>

    @Multipart
    @POST("predict")
    suspend fun pendeteksiBlog(
        @Part file: MultipartBody.Part
    ) : Response<PredictResponse>


}