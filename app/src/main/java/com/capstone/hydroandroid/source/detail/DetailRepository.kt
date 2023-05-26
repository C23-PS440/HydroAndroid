package com.capstone.hydroandroid.source.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.detail.DetailResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface DetailRepository{
    fun getDetailBlog(blogId: String) : LiveData<EventResult<DetailResponse>>
}

class DetailRepositoryImpl(private val dataSource: DetailRemoteDataSource): DetailRepository {
    override fun getDetailBlog(blogId: String): LiveData<EventResult<DetailResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getDetailBlog(blogId)
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