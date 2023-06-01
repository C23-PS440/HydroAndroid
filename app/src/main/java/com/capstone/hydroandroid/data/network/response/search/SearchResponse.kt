package com.capstone.hydroandroid.data.network.response.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("result")
	val result: List<ResultItem>,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("message")
	val message: String
)

data class ResultItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("blogId")
	val blogId: String
)
