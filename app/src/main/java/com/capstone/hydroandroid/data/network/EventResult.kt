package com.capstone.hydroandroid.data.network

sealed class EventResult<out R> private constructor() {
    data class Success<out T>(val data: T) : EventResult<T>()
    data class Error(val code: Int? = null, val error: String? = null) : EventResult<Nothing>()
    object Loading : EventResult<Nothing>()
}