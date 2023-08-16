package com.example.weatherapp.domain

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: DataSource,
    private val localDataSource: LocalDataSource
) {

    fun observeWeatherForecast(): Flow<List<DailyWeatherModel>> {
        return localDataSource.observeWeatherForecast()
    }

    suspend fun updateWeather(city: String): String {
        val result = dataSource.getWeatherForecast(city)
        val actualCity = result[0].city
        saveWeatherForecast(actualCity, result)
        return actualCity
    }

    private suspend fun saveWeatherForecast(city: String, dailyWeatherModelList: List<DailyWeatherModel>) {
        try {
            localDataSource.saveWeatherForecast(city, dailyWeatherModelList)
        } catch (ex: Exception) {
            Log.e("Repository", "save error", ex)
        }
    }
}