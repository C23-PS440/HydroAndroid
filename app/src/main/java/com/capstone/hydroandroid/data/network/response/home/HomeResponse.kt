package com.capstone.hydroandroid.data.network.response.home


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("blog")
    val blog: List<Blog>,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String
)