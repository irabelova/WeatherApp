package com.example.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DailyWeatherDbModel::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}