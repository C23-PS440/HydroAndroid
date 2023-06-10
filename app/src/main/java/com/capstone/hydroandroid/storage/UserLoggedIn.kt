package com.capstone.hydroandroid.storage

data class UserLoggedIn(
    val name: String,
    val userId: String,
    val token: String,
    val email: String
)