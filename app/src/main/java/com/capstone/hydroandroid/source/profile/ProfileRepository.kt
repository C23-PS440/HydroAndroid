package com.capstone.hydroandroid.source.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.profile.ProfileResponse
import com.capstone.hydroandroid.storage.AppLocalData
import com.capstone.hydroandroid.storage.UserLoggedIn
import kotlinx.coroutines.Dispatchers
import org.json.JSONObject

interface ProfileRepository{
    fun profile() : LiveData<EventResult<ProfileResponse>>
}

class ProfileRepositoryImpl(
    private val dataSource: ProfileRemoteDataSource
): ProfileRepository {
    override fun profile (): LiveData<EventResult<ProfileResponse>> =
        liveData(Dispatchers.IO){
            emit(EventResult.Loading)
            try {
                val response = dataSource.getProfile()
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