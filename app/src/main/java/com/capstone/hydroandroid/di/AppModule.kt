package com.capstone.hydroandroid.di

import com.capstone.hydroandroid.source.detail.DetailRemoteDataSource
import com.capstone.hydroandroid.source.detail.DetailRepository
import com.capstone.hydroandroid.source.detail.DetailRepositoryImpl
import com.capstone.hydroandroid.source.home.HomeRemoteDataSource
import com.capstone.hydroandroid.source.home.HomeRepository
import com.capstone.hydroandroid.source.home.HomeRepositoryImpl
import com.capstone.hydroandroid.source.login.LoginRemoteDataSource
import com.capstone.hydroandroid.source.login.LoginRepository
import com.capstone.hydroandroid.source.login.LoginRepositoryImpl
import com.capstone.hydroandroid.source.register.RegisterRemoteDataSource
import com.capstone.hydroandroid.source.register.RegisterRepository
import com.capstone.hydroandroid.source.register.RegisterRepositoryImpl
import com.capstone.hydroandroid.source.search.SearchRemoteDataSource
import com.capstone.hydroandroid.source.search.SearchRepository
import com.capstone.hydroandroid.source.search.SearchRepositoryImpl
import com.capstone.hydroandroid.source.video.VideoRemoteDataSource
import com.capstone.hydroandroid.source.video.VideoRepository
import com.capstone.hydroandroid.source.video.VideoRepositoryImpl
import com.capstone.hydroandroid.storage.AppLocalData
import com.capstone.hydroandroid.storage.SharedPreferencesStorage
import com.capstone.hydroandroid.storage.Storage
import com.capstone.hydroandroid.ui.detail.DetailViewModel
import com.capstone.hydroandroid.ui.home.HomeViewModel
import com.capstone.hydroandroid.ui.login.LoginViewModel
import com.capstone.hydroandroid.ui.register.RegisterViewModel
import com.capstone.hydroandroid.ui.search.SearchViewModel
import com.capstone.hydroandroid.ui.video.VideoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single{ RegisterRemoteDataSource(get()) }
    single{ LoginRemoteDataSource(get()) }
    single{ HomeRemoteDataSource(get()) }
    single{ DetailRemoteDataSource(get()) }
    single{ SearchRemoteDataSource(get()) }
    single{ VideoRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<RegisterRepository>{ RegisterRepositoryImpl(get()) }
    single<LoginRepository>{ LoginRepositoryImpl(get(), get()) }
    single<HomeRepository>{ HomeRepositoryImpl(get()) }
    single<DetailRepository>{ DetailRepositoryImpl(get()) }
    single<SearchRepository>{ SearchRepositoryImpl(get()) }
    single<VideoRepository>{ VideoRepositoryImpl(get()) }
}

val viewModelModule = module {
    single{ RegisterViewModel(get()) }
    single{ LoginViewModel(get()) }
    single{ HomeViewModel(get()) }
    single{ DetailViewModel(get()) }
    single{ SearchViewModel(get()) }
    single{ VideoViewModel(get()) }
}

val storageModule = module {
    single { AppLocalData(get()) }
    single<Storage> { SharedPreferencesStorage(androidContext()) }
}