package com.capstone.hydroandroid.data.network.response.home

import com.google.gson.annotations.SerializedName

data class HomeResponse(

	@field:SerializedName("blogs")
	val blogs: List<BlogsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class BlogsItem(

	@field:SerializedName("blogDescription")
	val blogDescription: String,

	@field:SerializedName("dateCreated")
	val dateCreated: String,

	@field:SerializedName("createdBy")
	val createdBy: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("blogId")
	val blogId: Int,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("blogTitle")
	val blogTitle: String
)
