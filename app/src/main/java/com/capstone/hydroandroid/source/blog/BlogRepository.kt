package com.capstone.hydroandroid.source.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.blog.UserBlogResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface BlogRepository{
    fun getAllUserBlog() : LiveData<EventResult<UserBlogResponse>>
}

class BlogRepositoryImpl(private val dataSource: BlogRemoteDataSource): BlogRepository {
    override fun getAllUserBlog (): LiveData<EventResult<UserBlogResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getAllUserBlog()
                if (response.isSuccessful){
                    val data = response.body()
                    data?.let {
                        emit(EventResult.Success(it))
                    }
                }
                val error = response.errorBody()?.string()
                if (error != null){
                    val jsonObject = JSONObject(error)
                    val message = jsonObject.getString("message")
                    emit(EventResult.Error(null, message))
                }
            }catch (e:Exception){
                emit(EventResult.Error(null, "Something went wrong"))
            }
        }
}