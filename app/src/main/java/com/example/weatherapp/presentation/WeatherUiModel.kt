package com.example.weatherapp.presentation

import com.example.weatherapp.domain.WeatherModel

sealed interface WeatherUiModel {
    object Loading: WeatherUiModel
    data class Data(val weather: List<WeatherModel>): WeatherUiModel
    object Error: WeatherUiModel
}