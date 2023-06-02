package com.capstone.hydroandroid.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.video.VideoResponse
import com.capstone.hydroandroid.source.video.VideoRepository

class VideoViewModel(private val repository: VideoRepository): ViewModel() {
    fun getAllVideo() : LiveData<EventResult<VideoResponse>> = repository.getAllVideo()
}