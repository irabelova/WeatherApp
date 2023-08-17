package com.example.weatherapp

import com.example.weatherapp.data.database.DailyWeatherDbModel
import com.example.weatherapp.data.network.models.ConditionDto
import com.example.weatherapp.data.network.models.DailyWeatherDto
import com.example.weatherapp.data.network.models.ForecastDayDto
import com.example.weatherapp.data.network.models.ForecastDto
import com.example.weatherapp.data.network.models.LocationDto
import com.example.weatherapp.data.network.models.WeatherDto
import com.example.weatherapp.domain.DailyWeatherModel

val dailyWeatherModel = DailyWeatherModel(
    date = "2023-08-17",
    city = "Moscow",
    avgTemp = 22.5f,
    maxWind = 17.7f,
    avgHumidity = 67,
    text = "Sunny",
    icon = "https://cdn.weatherapi.com/weather/64x64/day/116.png"
)

val dailyWeatherDbModel = DailyWeatherDbModel(
    id = 0,
    date = "2023-08-17",
    city = "Moscow",
    avgTemp = 22.5f,
    maxWind = 17.7f,
    avgHumidity = 67,
    text = "Sunny",
    icon = "https://cdn.weatherapi.com/weather/64x64/day/116.png"
)

val forecastDayDto = ForecastDayDto(
    date = "2023-08-17",
    day = DailyWeatherDto(
        avgTemp = 22.5f,
        maxWind = 17.7f,
        avgHumidity = 67,
        condition = ConditionDto(
            text = "Sunny",
            icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
        )
    )
)

val weatherDto = WeatherDto(
    location = LocationDto(name = "Moscow"),
    forecast = ForecastDto(listOf(forecastDayDto))
)