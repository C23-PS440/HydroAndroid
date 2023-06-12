package com.capstone.hydroandroid.source.pendeteksi

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.pendeteksi.PredictResponse
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import okhttp3.MultipartBody
import org.json.JSONObject

interface PendeteksiRepository {
    fun pendeteksi(
        file: MultipartBody.Part
    ): LiveData<EventResult<PredictResponse>>
}


class PendeteksiRepositoryImpl(private val dataSource: PendeteksiRemoteDataSource) :
    PendeteksiRepository {
    override fun pendeteksi(file: MultipartBody.Part): LiveData<EventResult<PredictResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.pendeteksi(file)
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