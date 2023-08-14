package com.example.weatherapp.domain

import com.example.weatherapp.data.network.WeatherDtoModel
import javax.inject.Inject

class Mapper @Inject constructor(){
    fun weatherDtoToWeather(weatherDto: WeatherDtoModel) =
        WeatherModel(
            date = weatherDto.date,
            city = weatherDto.city,
            avgTemp = weatherDto.avgTemp,
            maxWind = weatherDto.maxWind,
            avgHumidity = weatherDto.avgHumidity,
            text = weatherDto.text,
            icon = weatherDto.icon
        )
}