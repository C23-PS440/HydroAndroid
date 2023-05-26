package com.capstone.hydroandroid.source.detail

import com.capstone.hydroandroid.data.network.service.BlogService

class DetailRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getDetailBlog(blogId: String) = blogService.getDetailBlog(blogId)
}