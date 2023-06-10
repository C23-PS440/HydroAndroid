package com.capstone.hydroandroid.source.blog

import com.capstone.hydroandroid.data.network.service.BlogService
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BlogRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getAllUserBlog() = blogService.getAllUserBlog()
    suspend fun uploadBlog(
        file: MultipartBody.Part,
        blogTitle: RequestBody,
        blogDescription: RequestBody
    ) = blogService.uploadBlog(file, blogTitle, blogDescription)
}