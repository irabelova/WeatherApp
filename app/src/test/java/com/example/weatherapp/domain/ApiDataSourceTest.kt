package com.example.weatherapp.domain

import com.example.weatherapp.dailyWeatherModel
import com.example.weatherapp.data.network.WeatherApiService
import com.example.weatherapp.weatherDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApiDataSourceTest {
    @Mock
    private lateinit var api: WeatherApiService

    @Mock
    private lateinit var mapper: NetworkMapper

    private lateinit var dataSource: ApiDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataSource = ApiDataSource(api, mapper)
    }

    @Test
    fun `get weather forecast test`() = runTest {
        val city = "Moscow"
        Mockito.`when`(api.getWeatherForecastByCity(city = city))
            .thenReturn(weatherDto)
        Mockito.`when`(mapper.weatherDtoToDailyWeatherModel(weatherDto))
            .thenReturn(listOf(dailyWeatherModel))
        assertEquals(listOf(dailyWeatherModel), dataSource.getWeatherForecast(city))
    }
}