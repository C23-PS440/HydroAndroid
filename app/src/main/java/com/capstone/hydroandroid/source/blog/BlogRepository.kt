package com.capstone.hydroandroid.source.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.addBlog.AddBlogResponse
import com.capstone.hydroandroid.data.network.response.blog.UserBlogResponse
import kotlinx.coroutines.Dispatchers
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject

interface BlogRepository{
    fun getAllUserBlog() : LiveData<EventResult<UserBlogResponse>>

    fun uploadBlog(
        file: MultipartBody.Part,
        blogTitle: RequestBody,
        blogDescription: RequestBody
    ) : LiveData<EventResult<AddBlogResponse>>
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

    override fun uploadBlog(
        file: MultipartBody.Part,
        blogTitle: RequestBody,
        blogDescription: RequestBody
    ): LiveData<EventResult<AddBlogResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.uploadBlog(file,blogTitle,blogDescription)
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