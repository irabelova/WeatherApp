package com.example.weatherapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather")
data class DailyWeatherDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val city: String,
    val avgTemp: Float,
    val maxWind: Float,
    val avgHumidity: Int,
    val text: String,
    val icon: String,
)