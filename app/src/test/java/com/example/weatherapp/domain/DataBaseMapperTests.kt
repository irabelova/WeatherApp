package com.example.weatherapp.domain

import com.example.weatherapp.dailyWeatherDbModel
import com.example.weatherapp.dailyWeatherModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataBaseMapperTests {
    private lateinit var mapper: DataBaseMapper

    @Before
    fun setup() {
        mapper = DataBaseMapper()
    }


    @Test
    fun `daily weather model to daily weather db model test`() {
        Assert.assertEquals(
            dailyWeatherDbModel,
            mapper.dailyWeatherModelToDailyWeatherDbModel(dailyWeatherModel)
        )
    }

    @Test
    fun `daily weather db model to daily weather model test`() {
        Assert.assertEquals(
            dailyWeatherModel,
            mapper.dailyWeatherDbModelToDailyWeatherModel(dailyWeatherDbModel)
        )
    }
}