package com.example.weatherapp.domain

import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: DataSource
) {
    suspend fun getWeatherForecast(city: String): List<WeatherModel> {
        return dataSource.getWeatherForecast(city)
    }
}