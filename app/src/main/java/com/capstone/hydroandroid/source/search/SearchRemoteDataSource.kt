package com.capstone.hydroandroid.source.search

import com.capstone.hydroandroid.data.network.service.BlogService

class SearchRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getSearchedBlog(query: String) = blogService.getSearchedBlog(query)
}