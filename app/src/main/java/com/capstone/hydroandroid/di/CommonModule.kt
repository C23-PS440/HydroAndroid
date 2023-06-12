package com.capstone.hydroandroid.di

import com.capstone.hydroandroid.data.network.service.AuthService
import com.capstone.hydroandroid.data.network.service.BlogService
import com.capstone.hydroandroid.storage.AccessTokenInterceptor
import com.capstone.hydroandroid.storage.AppLocalData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://baa8-125-166-118-148.ngrok-free.app/latihan/"

private const val BASE_URL_API = "https://28d2-2001-448a-106d-4807-6dca-d607-7c78-5996.ngrok-free.app/"

val databaseModule = module {

}

val networkModule = module {
    single { createOkHttpClient(get()) }
    factory { createAccessTokenInterceptor(get()) }
    factory { createConverterFactory() }
    factory { createService<AuthService>(get(),get()) }
    factory { createService<BlogService>(get(),get()) }
}

private fun createOkHttpClient(accessTokenInterceptor: AccessTokenInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createHttpLoggingInterceptor())
        .addInterceptor(accessTokenInterceptor)

        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}
private fun createHttpLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private fun createAccessTokenInterceptor(
    appLocalData: AppLocalData
): AccessTokenInterceptor {
    return AccessTokenInterceptor(appLocalData)
}

private fun createConverterFactory(): GsonConverterFactory {
    return GsonConverterFactory.create()
}

private inline fun <reified T> createService(
    okHttpClient: OkHttpClient,
    converterFactory: GsonConverterFactory,
    baseUrl: String = BASE_URL_API
): T {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()
        .create(T::class.java)
}
