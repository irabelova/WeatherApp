package com.example.weatherapp.di

import com.example.weatherapp.domain.ApiDataSource
import com.example.weatherapp.domain.DataBaseSource
import com.example.weatherapp.domain.DataSource
import com.example.weatherapp.domain.LocalDataSource
import dagger.Binds
import dagger.Module

@Module(includes = [DataBaseModule::class, NetworkModule::class])
abstract class DomainModule {

    @Binds
    abstract fun bindDataSource(source: ApiDataSource): DataSource

    @Binds
    abstract fun bindLocalDataSource(source: DataBaseSource): LocalDataSource
}