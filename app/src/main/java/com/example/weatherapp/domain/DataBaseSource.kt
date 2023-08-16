package com.example.weatherapp.domain

import com.example.weatherapp.data.database.WeatherDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataBaseSource @Inject constructor(
    private val weatherDao: WeatherDao,
    private val mapper: DataBaseMapper
): LocalDataSource {
    override fun observeWeatherForecast(): Flow<List<DailyWeatherModel>> {
        return weatherDao.observeWeatherForecast()
            .map {
                it.map { entity ->  mapper.dailyWeatherDbModelToDailyWeatherModel(entity)}
            }
    }

    override suspend fun saveWeatherForecast(city: String, dailyWeatherModel: List<DailyWeatherModel>) {
        weatherDao.updateByCity(city, dailyWeatherModel.map {
            mapper.dailyWeatherModelToDailyWeatherDbModel(it)
        })
    }
}