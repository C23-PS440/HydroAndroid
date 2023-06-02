package com.capstone.hydroandroid.source.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.video.VideoResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface VideoRepository{
    fun getAllVideo() : LiveData<EventResult<VideoResponse>>
}

class VideoRepositoryImpl(private val dataSource: VideoRemoteDataSource): VideoRepository {
    override fun getAllVideo(): LiveData<EventResult<VideoResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getALlVideo()
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