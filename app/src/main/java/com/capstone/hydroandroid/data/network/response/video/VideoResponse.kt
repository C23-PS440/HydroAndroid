package com.capstone.hydroandroid.data.network.response.video


import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("video")
    val video: List<Video>
)