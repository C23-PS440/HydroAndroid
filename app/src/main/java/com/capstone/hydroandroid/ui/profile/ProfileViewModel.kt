package com.capstone.hydroandroid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.profile.ProfileResponse
import com.capstone.hydroandroid.source.profile.ProfileRepository

class ProfileViewModel(private val repository: ProfileRepository): ViewModel() {
    fun getProfile() : LiveData<EventResult<ProfileResponse>> {
        return repository.profile()
    }
}