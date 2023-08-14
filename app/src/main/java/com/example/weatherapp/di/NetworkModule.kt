package com.example.weatherapp.di

import com.example.weatherapp.data.network.WeatherApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl

@Module
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://api.weatherapi.com/v1/"
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofit(moshi: Moshi, @BaseUrl baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideFoodApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create()
    }

}