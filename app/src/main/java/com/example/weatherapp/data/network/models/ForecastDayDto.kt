package com.example.weatherapp.data.network.models

data class ForecastDayDto (
    val date: String,
    val day: DailyWeatherDto
)