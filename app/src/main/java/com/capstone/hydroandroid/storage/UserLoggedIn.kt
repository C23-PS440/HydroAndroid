package com.capstone.hydroandroid.storage

data class UserLoggedIn(
    val username: String,
    val email: String,
    val accessToken: String
)