package com.example.weatherapp.domain

interface DataSource {
    suspend fun getWeatherForecast(city: String): List<DailyWeatherModel>
}