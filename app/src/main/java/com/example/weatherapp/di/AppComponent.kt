package com.example.weatherapp.di

import android.content.Context
import com.example.weatherapp.presentation.WeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DomainModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
)
interface AppComponent {

    fun inject(weatherFragment: WeatherFragment)

    @Component.Factory
    interface AppComponentFactory {

        fun create(@BindsInstance context: Context): AppComponent
    }

}