package com.capstone.hydroandroid.data.network.response.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("response")
	val response: List<ResponseItem?>? = null,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ResponseItem(

	@field:SerializedName("blogDescription")
	val blogDescription: String? = null,

	@field:SerializedName("dateCreated")
	val dateCreated: String? = null,

	@field:SerializedName("createdBy")
	val createdBy: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("blogId")
	val blogId: Int? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("blogTitle")
	val blogTitle: String? = null
)
