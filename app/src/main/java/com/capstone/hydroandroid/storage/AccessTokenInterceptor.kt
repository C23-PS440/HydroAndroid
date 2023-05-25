package com.capstone.hydroandroid.storage

import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor (
    private val appLocalData: AppLocalData
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = appLocalData.getKeyAccessToken
        return if (!token.isNullOrEmpty()) {
            val authenticatedRequestBody = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $token")
                .build()
            val response = chain.proceed(authenticatedRequestBody)
            response
        } else {
            val authenticatedRequestBody = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build()
            chain.proceed(authenticatedRequestBody)
        }
    }
}