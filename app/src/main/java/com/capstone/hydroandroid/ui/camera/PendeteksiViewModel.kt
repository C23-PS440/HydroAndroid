package com.capstone.hydroandroid.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.pendeteksi.PredictResponse
import com.capstone.hydroandroid.source.pendeteksi.PendeteksiRepository
import okhttp3.MultipartBody

class PendeteksiViewModel(private val repository : PendeteksiRepository): ViewModel() {
    fun pendeteksi(file : MultipartBody.Part) : LiveData<EventResult<PredictResponse>> =
        repository.pendeteksi(file)
}