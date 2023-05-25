package com.capstone.hydroandroid.storage

data class UserLoggedIn(
    val accessToken: String,
    val name: String,
    val userId: String
)