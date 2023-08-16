package com.example.weatherapp.domain

import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getWeatherForecast(city: String): List<DailyWeatherModel>
}

interface LocalDataSource{

    fun observeWeatherForecast(): Flow<List<DailyWeatherModel>>

    suspend fun saveWeatherForecast(city: String, dailyWeatherModel: List<DailyWeatherModel>)
}