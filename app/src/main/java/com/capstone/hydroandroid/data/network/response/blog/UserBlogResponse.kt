package com.capstone.hydroandroid.data.network.response.blog

import com.google.gson.annotations.SerializedName

data class UserBlogResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("blogs")
	val blog: List<BlogsItem?>? = null
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
