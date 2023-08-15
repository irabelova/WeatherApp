package com.example.weatherapp.domain

import com.example.weatherapp.data.network.models.WeatherDto
import javax.inject.Inject

class Mapper @Inject constructor() {

     fun weatherDtoToDailyWeatherModel(
        weatherDto: WeatherDto
    ): List<DailyWeatherModel> {
        return weatherDto.forecast.forecastday.map {
            DailyWeatherModel(
                date = it.date,
                city = weatherDto.location.name,
                avgTemp = it.day.avgTemp,
                maxWind = it.day.maxWind,
                avgHumidity = it.day.avgHumidity,
                text = it.day.condition.text,
                icon = "https:${it.day.condition.icon}"
            )
        }
    }
}