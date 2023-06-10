package com.capstone.hydroandroid.source.splash

import com.capstone.hydroandroid.storage.AppLocalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SplashRepository{
    fun isUserHasLoggedIn(): Flow<Boolean>
}

class SplashRepositoryImpl(private val appLocalData: AppLocalData): SplashRepository {
    override fun isUserHasLoggedIn(): Flow<Boolean> = flow {
        val isUserHasLoggedIn = appLocalData.isUserHasLoggedIn
        emit(isUserHasLoggedIn)
    }

}