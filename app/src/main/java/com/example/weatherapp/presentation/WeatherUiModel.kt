package com.example.weatherapp.presentation

import com.example.weatherapp.domain.DailyWeatherModel

sealed interface WeatherUiModel {
    object Loading: WeatherUiModel
    data class Data(val weather: List<DailyWeatherModel>): WeatherUiModel
    object Error: WeatherUiModel

    object Empty: WeatherUiModel
}