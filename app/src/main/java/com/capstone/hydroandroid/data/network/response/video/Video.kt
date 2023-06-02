package com.capstone.hydroandroid.data.network.response.video


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("videoUrl")
    val videoUrl: String
)