package com.capstone.hydroandroid.source.home

import com.capstone.hydroandroid.data.network.service.BlogService

class HomeRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getAllBlog() = blogService.getAllBlog()
}