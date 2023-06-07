package com.capstone.hydroandroid.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.blog.UserBlogResponse
import com.capstone.hydroandroid.source.blog.BlogRepository

class BlogViewModel(private val repository: BlogRepository): ViewModel() {
    fun getAllUserBlog () : LiveData<EventResult<UserBlogResponse>> {
        return repository.getAllUserBlog()
    }
}