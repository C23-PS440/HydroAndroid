package com.capstone.hydroandroid.data.network.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("blogId")
	val blogId: String
)
