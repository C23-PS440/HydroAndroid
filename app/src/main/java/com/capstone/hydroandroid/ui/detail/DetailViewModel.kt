package com.capstone.hydroandroid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.detail.DetailResponse
import com.capstone.hydroandroid.source.detail.DetailRepository

class DetailViewModel(private val repository: DetailRepository): ViewModel() {
    fun getDetailBlog (blogId: String) : LiveData<EventResult<DetailResponse>> {
        return repository.getDetailBlog(blogId)
    }
}