package com.capstone.hydroandroid.data.network.response.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("user_blog")
	val userBlog: List<UserBlogItem>,

	@field:SerializedName("userId")
	val userId: String,

	@field:SerializedName("user_scan")
	val userScan: List<UserScanItem>,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)

data class UserScanItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("scanId")
	val scanId: String,

	@field:SerializedName("nama_tanaman")
	val namaTanaman: String,

	@field:SerializedName("nama_penyakit")
	val namaPenyakit: String
)

data class UserBlogItem(

	@field:SerializedName("photoUrl")
	val photoUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("blogId")
	val blogId: String
)
