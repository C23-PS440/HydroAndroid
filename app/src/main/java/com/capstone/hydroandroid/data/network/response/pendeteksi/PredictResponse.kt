package com.capstone.hydroandroid.data.network.response.pendeteksi

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("response")
	val response: Response,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Response(

	@field:SerializedName("diseaseName")
	val diseaseName: String,

	@field:SerializedName("solution")
	val solution: String,

	@field:SerializedName("diseaseCause")
	val diseaseCause: String,

	@field:SerializedName("index")
	val index: Int? = null,

	@field:SerializedName("plantName")
	val plantName: String,

	@field:SerializedName("diseaseRecognition")
	val diseaseRecognition: String
)
