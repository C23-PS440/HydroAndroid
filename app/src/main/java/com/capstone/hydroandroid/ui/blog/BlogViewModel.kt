package com.capstone.hydroandroid.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.addBlog.AddBlogResponse
import com.capstone.hydroandroid.data.network.response.blog.UserBlogResponse
import com.capstone.hydroandroid.source.blog.BlogRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class BlogViewModel(private val repository: BlogRepository): ViewModel() {
    fun getAllUserBlog () : LiveData<EventResult<UserBlogResponse>> {
        return repository.getAllUserBlog()
    }

    fun uploadBlog(
        file: MultipartBody.Part,
        blogTitle: RequestBody,
        blogDescription: RequestBody

    ) : LiveData<EventResult<AddBlogResponse>> = repository.uploadBlog(file,blogTitle, blogDescription)

}