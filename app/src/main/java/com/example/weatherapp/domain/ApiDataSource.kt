package com.example.weatherapp.domain

import com.example.weatherapp.data.network.WeatherApiService
import javax.inject.Inject

class ApiDataSource  @Inject constructor(
    private val service: WeatherApiService,
    private val mapper: Mapper
) : DataSource {
    override suspend fun getWeatherForecast(city: String): List<WeatherModel> {
        return service.getWeatherForecastByCity(city = city)
            .map { mapper.weatherDtoToWeather(it) }
    }

}