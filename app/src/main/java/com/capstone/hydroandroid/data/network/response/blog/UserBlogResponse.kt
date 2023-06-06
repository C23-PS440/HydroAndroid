package com.capstone.hydroandroid.data.network.response.blog

import com.capstone.hydroandroid.data.network.response.home.Blog
import com.google.gson.annotations.SerializedName

data class UserBlogResponse(

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("blog")
	val blog: List<Blog?>? = null
)

data class BlogItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("blogId")
	val blogId: String? = null
)
