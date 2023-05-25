package com.capstone.hydroandroid.storage

interface Storage {
    fun setString(key: String, value: String)
    fun getString(key: String): String?
    fun remove(key: String)
    fun setBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
}