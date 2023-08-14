package com.example.weatherapp.data.network

import com.squareup.moshi.Json
    data class WeatherDtoModel(
        val date: String,
        @Json(name = "name")
        val city: String,
        @Json(name = "avgtemp_c")
        val avgTemp: Float,
        @Json(name = "maxwind_kph")
        val maxWind: Float,
        val avgHumidity: Int,
        val text: String,
        val icon: String
    )
