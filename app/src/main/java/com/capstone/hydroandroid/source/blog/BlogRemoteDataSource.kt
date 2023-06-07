package com.capstone.hydroandroid.source.blog

import com.capstone.hydroandroid.data.network.service.BlogService

class BlogRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getAllUserBlog() = blogService.getAllUserBlog()
}