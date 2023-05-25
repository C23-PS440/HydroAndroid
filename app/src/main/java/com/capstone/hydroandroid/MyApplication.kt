package com.capstone.hydroandroid

import android.app.Application
import com.capstone.hydroandroid.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin{
            androidContext(this@MyApplication)
            modules(
                remoteDataSourceModule,
                repositoryModule,
                viewModelModule,
                storageModule,
                networkModule,
                databaseModule
            )
        }
    }
}