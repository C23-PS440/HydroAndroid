package com.capstone.hydroandroid.source.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.RegisterRequest
import com.capstone.hydroandroid.data.network.response.register.RegisterResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface RegisterRepository{
    fun register(registerRequest: RegisterRequest) : LiveData<EventResult<RegisterResponse>>
}

class RegisterRepositoryImpl(private val dataSource:RegisterRemoteDataSource):RegisterRepository {
    override fun register(registerRequest: RegisterRequest): LiveData<EventResult<RegisterResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.register(registerRequest)
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