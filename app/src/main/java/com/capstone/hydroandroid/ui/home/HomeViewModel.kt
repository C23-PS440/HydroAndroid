package com.capstone.hydroandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.data.network.EventResult
import com.capstone.hydroandroid.data.network.response.home.HomeResponse
import com.capstone.hydroandroid.source.home.HomeRepository
import com.capstone.hydroandroid.storage.AppLocalData

class HomeViewModel(private val repository: HomeRepository,private val appLocalData: AppLocalData): ViewModel() {
    fun getAllBlog () : LiveData<EventResult<HomeResponse>> {
        return repository.getAllBlog()
    }
    fun getUsername() : String? = appLocalData.getUsername
}