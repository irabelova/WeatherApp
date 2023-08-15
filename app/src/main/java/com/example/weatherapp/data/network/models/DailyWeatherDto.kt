package com.example.weatherapp.data.network.models

import com.squareup.moshi.Json
    data class DailyWeatherDto(
        @Json(name = "avgtemp_c")
        val avgTemp: Float,
        @Json(name = "maxwind_kph")
        val maxWind: Float,
        @Json(name = "avghumidity")
        val avgHumidity: Int,
        val condition: ConditionDto
    )
