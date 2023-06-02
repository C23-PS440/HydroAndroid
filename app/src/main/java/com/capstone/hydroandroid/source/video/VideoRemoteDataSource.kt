package com.capstone.hydroandroid.source.video

import com.capstone.hydroandroid.data.network.service.BlogService

class VideoRemoteDataSource constructor(private val blogService: BlogService) {
    suspend fun getALlVideo() = blogService.getAllVideo()
}