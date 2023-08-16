package com.example.weatherapp.domain

import com.example.weatherapp.data.database.DailyWeatherDbModel
import javax.inject.Inject

class DataBaseMapper @Inject constructor(){

    fun dailyWeatherModelToDailyWeatherDbModel(
        dailyWeatherModel: DailyWeatherModel
    ): DailyWeatherDbModel {
        return DailyWeatherDbModel(
            id = 0,
            date = dailyWeatherModel.date,
            city = dailyWeatherModel.city,
            avgTemp = dailyWeatherModel.avgTemp,
            maxWind = dailyWeatherModel.maxWind,
            avgHumidity = dailyWeatherModel.avgHumidity,
            text = dailyWeatherModel.text,
            icon = dailyWeatherModel.icon,
        )
    }

    fun dailyWeatherDbModelToDailyWeatherModel(
        dailyWeatherDbModel: DailyWeatherDbModel
    ): DailyWeatherModel{
        return DailyWeatherModel(
            date = dailyWeatherDbModel.date,
            city = dailyWeatherDbModel.city,
            avgTemp = dailyWeatherDbModel.avgTemp,
            maxWind = dailyWeatherDbModel.maxWind,
            avgHumidity = dailyWeatherDbModel.avgHumidity,
            text = dailyWeatherDbModel.text,
            icon = dailyWeatherDbModel.icon,
        )
    }
}