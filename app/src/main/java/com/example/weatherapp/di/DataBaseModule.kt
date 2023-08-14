package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.data.database.WeatherDao
import com.example.weatherapp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @Provides
    fun getDataSource(context: Context): WeatherDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather_database"
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    fun provideDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }
}