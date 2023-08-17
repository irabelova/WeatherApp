package com.example.weatherapp.domain

import com.example.weatherapp.dailyWeatherModel
import com.example.weatherapp.weatherDto
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class NetworkMapperTests() {
    private lateinit var mapper: NetworkMapper

    @Before
    fun setup() {
        mapper = NetworkMapper()
    }


    @Test
    fun `weather dto to daily weather model test` () {
        assertEquals(
            listOf(dailyWeatherModel),
            mapper.weatherDtoToDailyWeatherModel(weatherDto)
        )
    }
}

