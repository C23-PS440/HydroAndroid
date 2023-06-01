package com.capstone.hydroandroid.source.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.search.SearchResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface SearchRepository{
    fun getSearchedBlog(query: String) : LiveData<EventResult<SearchResponse>>
}

class SearchRepositoryImpl(private val dataSource:SearchRemoteDataSource): SearchRepository {
    override fun getSearchedBlog(query: String): LiveData<EventResult<SearchResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getSearchedBlog(query)
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