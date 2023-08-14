package com.example.weatherapp.domain

data class WeatherModel (
    val date: String,
    val city: String,
    val avgTemp: Float,
    val maxWind: Float,
    val avgHumidity: Int,
    val text: String,
    val icon: String
)