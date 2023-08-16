package com.example.weatherapp.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WeatherDao {

    @Query("SELECT * FROM Weather")
    abstract fun observeWeatherForecast(): Flow<List<DailyWeatherDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertWeatherForecast(dailyWeatherDbList: List<DailyWeatherDbModel>)

    @Query("DELETE  FROM Weather WHERE city LIKE '%' || :city || '%'")
    abstract suspend fun deleteByCity(city: String)

    @Transaction
    open suspend fun updateByCity(city: String, weatherList: List<DailyWeatherDbModel>) {
        deleteByCity(city)
        insertWeatherForecast(weatherList)
    }
}























































































































































