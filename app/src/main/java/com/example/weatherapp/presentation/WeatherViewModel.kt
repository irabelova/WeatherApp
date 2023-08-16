package com.example.weatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.Repository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var searchPerformed = false

    private val searchQuery = MutableStateFlow(DEFAULT_CITY)
    private val isLoading = MutableStateFlow(false)
    private val actualCity = MutableStateFlow<String?>(null)

    @OptIn(FlowPreview::class)
    private val queryFlow = searchQuery
        .debounce(400L)
        .distinctUntilChanged()

    private val filterQueryFlow = queryFlow
        .filter { it.length > MIN_LETTERS_SEARCH }
    private val filteredForecast = combine(
        actualCity, filterQueryFlow, repository.observeWeatherForecast()
    ) { actual, fromKeyboard, forecast ->
        val filter = (actual ?: fromKeyboard).lowercase()
        val filteredForecast = forecast
            .filter {
                it.city.lowercase()
                    .contains(filter)
            }
        if (actual == null && filteredForecast.isNotEmpty()) {
            val actualFilter = filteredForecast[0].city
            filteredForecast.filter {
                it.city.contains(actualFilter)
            }
        } else {
            filteredForecast
        }
    }


    val weatherForecast = combine(
        filteredForecast, isLoading
    ) { forecast, loading ->
        if (loading) WeatherUiModel.Loading
        else if (forecast.isEmpty()) WeatherUiModel.Empty
        else {
            WeatherUiModel.Data(forecast)
        }

    }.catch {
        Log.e("WeatherViewModel", "Flow error", it)
        emit(WeatherUiModel.Error)
    }

    init {
        updateWeatherForecast(DEFAULT_CITY)
        collectQueryFlow()
    }

    fun setTitle(title: String) {
        searchQuery.value = title
        searchPerformed = true
    }

    private fun updateWeatherForecast(city: String) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                actualCity.value = repository.updateWeather(city)
            } catch (e: Throwable) {
                actualCity.value = null
                Log.e("WeatherViewModel", "update error", e)
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun collectQueryFlow() {
        viewModelScope.launch {
            queryFlow.collectLatest {
                if (it.length > MIN_LETTERS_SEARCH) {
                    updateWeatherForecast(it)
                } else if (it.isEmpty() && searchPerformed) {
                    searchQuery.value = DEFAULT_CITY
                }
            }

        }
    }

    class WeatherFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WeatherViewModel(repository) as T

        }
    }

    private companion object {
        const val DEFAULT_CITY = "Moscow"
        const val MIN_LETTERS_SEARCH = 2
    }
}