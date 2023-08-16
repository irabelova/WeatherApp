package com.example.weatherapp.domain

import com.example.weatherapp.data.network.WeatherApiService
import javax.inject.Inject

class ApiDataSource  @Inject constructor(
    private val service: WeatherApiService,
    private val mapper: NetworkMapper
) : DataSource {

    override suspend fun getWeatherForecast(city: String): List<DailyWeatherModel> {
        val weatherDto = service.getWeatherForecastByCity(city = city)
        return mapper.weatherDtoToDailyWeatherModel(weatherDto)
    }
}