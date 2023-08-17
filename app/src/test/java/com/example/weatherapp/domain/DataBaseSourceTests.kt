package com.example.weatherapp.domain

import com.example.weatherapp.dailyWeatherDbModel
import com.example.weatherapp.dailyWeatherModel
import com.example.weatherapp.data.database.WeatherDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DataBaseSourceTests {

    @Mock
    private lateinit var dao: WeatherDao

    @Mock
    private lateinit var mapper: DataBaseMapper
    private lateinit var dataSource: DataBaseSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataSource = DataBaseSource(dao, mapper)
    }

    @Test
    fun `observe weather forecast test`() = runTest {
        Mockito.`when`(mapper.dailyWeatherDbModelToDailyWeatherModel(dailyWeatherDbModel))
            .thenReturn(dailyWeatherModel)
        Mockito.`when`(dao.observeWeatherForecast())
            .thenReturn(flow { emit(listOf(dailyWeatherDbModel)) })
        val result = dataSource.observeWeatherForecast()
        assertEquals(listOf(dailyWeatherModel), result.first())
    }

    @Test
    fun `save weather forecast test`() = runTest {
        val city = "Moscow"
        Mockito.`when`(mapper.dailyWeatherModelToDailyWeatherDbModel(dailyWeatherModel))
            .thenReturn(dailyWeatherDbModel)
        dataSource.saveWeatherForecast(city, listOf(dailyWeatherModel))
        Mockito.verify(dao, Mockito.times(1)).updateByCity(city, listOf(dailyWeatherDbModel))
    }
}