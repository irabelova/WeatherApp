package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Repository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: Repository) : ViewModel() {

    private val _state = MutableLiveData<WeatherUiModel>()
    val state: LiveData<WeatherUiModel> = _state

    private var searchPerformed = false

    private val searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val queryFlow = searchQuery.debounce(400L).distinctUntilChanged()

    init {
        getWeatherForecast()
        collectQueryFlow()
    }

    fun setTitle(title: String) {
        searchQuery.value = title
        searchPerformed = true
    }

    private fun getWeatherForecast() {
        viewModelScope.launch {
            _state.value = WeatherUiModel.Loading
            try {
                _state.value = WeatherUiModel.Data(
                    repository.getWeatherForecast("Moscow")
                )
            } catch (e: Exception) {
                Log.e("WeatherViewModel", "", e)
                _state.value = WeatherUiModel.Error
            }
        }
    }

    private fun collectQueryFlow() {
        viewModelScope.launch {
            queryFlow.collectLatest {
                if (it.length > 2) {
                    _state.value = WeatherUiModel.Loading
                    try {
                        _state.value = WeatherUiModel.Data(
                            repository.getWeatherForecast(it)
                        )
                    } catch (e: Exception) {
                        Log.e("WeatherViewModel", "", e)
                        _state.value = WeatherUiModel.Error
                    }
                } else if (it.isEmpty() && searchPerformed) {
                    getWeatherForecast()
                }
            }

        }
    }

    class WeatherFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WeatherViewModel(repository) as T

        }
    }
}