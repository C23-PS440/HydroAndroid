package com.capstone.hydroandroid.ui.profile

import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.source.profile.ProfileRepository
import com.capstone.hydroandroid.storage.AppLocalData

class ProfileViewModel(private val repository: ProfileRepository, private val appLocalData: AppLocalData): ViewModel() {

    fun getUsername() : String? = appLocalData.getUsername

    fun getEmail() : String? = appLocalData.getEmail

    fun logout() = appLocalData.dropUserLoggedIn()
}