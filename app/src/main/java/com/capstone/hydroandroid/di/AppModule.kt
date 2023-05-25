package com.capstone.hydroandroid.di

import com.capstone.hydroandroid.source.login.LoginRemoteDataSource
import com.capstone.hydroandroid.source.login.LoginRepository
import com.capstone.hydroandroid.source.login.LoginRepositoryImpl
import com.capstone.hydroandroid.source.register.RegisterRemoteDataSource
import com.capstone.hydroandroid.source.register.RegisterRepository
import com.capstone.hydroandroid.source.register.RegisterRepositoryImpl
import com.capstone.hydroandroid.storage.AppLocalData
import com.capstone.hydroandroid.storage.SharedPreferencesStorage
import com.capstone.hydroandroid.storage.Storage
import com.capstone.hydroandroid.ui.login.LoginViewModel
import com.capstone.hydroandroid.ui.register.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single{RegisterRemoteDataSource(get())}
    single{LoginRemoteDataSource(get())}
}

val repositoryModule = module {
    single<RegisterRepository>{RegisterRepositoryImpl(get())}
    single<LoginRepository>{LoginRepositoryImpl(get())}
}

val viewModelModule = module {
    single{RegisterViewModel(get())}
    single{LoginViewModel(get())}
}

val storageModule = module {
    single { AppLocalData(get()) }
    single<Storage> { SharedPreferencesStorage(androidContext()) }
}