package com.example.weatherapp.data.network

import com.example.weatherapp.BuildConfig.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeatherForecastByCity(
        @Query("key") key: String = API_KEY,
        @Query("days") days: Int = 5,
        @Query("q") city: String
    ): List<WeatherDtoModel>
}