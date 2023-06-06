package com.capstone.hydroandroid.source.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface HomeRepository{
    fun getAllBlog() : LiveData<EventResult<HomeResponse>>
}

class HomeRepositoryImpl(private val dataSource: HomeRemoteDataSource): HomeRepository {
    override fun getAllBlog (): LiveData<EventResult<HomeResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getAllBlog()
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