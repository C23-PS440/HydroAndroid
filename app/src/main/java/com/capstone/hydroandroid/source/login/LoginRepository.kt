package com.capstone.hydroandroid.source.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.request.LoginRequest
import com.capstone.hydroandroid.data.network.response.login.LoginResponse
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface LoginRepository{
    fun login(loginRequest: LoginRequest) : LiveData<EventResult<LoginResponse>>
}

class LoginRepositoryImpl(private val dataSource: LoginRemoteDataSource): LoginRepository {
    override fun login (loginRequest: LoginRequest): LiveData<EventResult<LoginResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.login(loginRequest)
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