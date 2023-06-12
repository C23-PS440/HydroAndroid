package com.capstone.hydroandroid.data.network.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("response")
	val response: Response,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class Response(

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
