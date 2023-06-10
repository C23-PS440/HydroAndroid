package com.capstone.hydroandroid.ui.splash

import androidx.lifecycle.ViewModel
import com.capstone.hydroandroid.source.splash.SplashRepository

class SplashViewModel(private val repository: SplashRepository) : ViewModel() {
    val isLogin = repository.isUserHasLoggedIn()
}