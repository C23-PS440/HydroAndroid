package com.capstone.hydroandroid.data.network.response.home


import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("blogId")
    val blogId: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("photoUrl")
    val photoUrl: String,
    @SerializedName("title")
    val title: String
)