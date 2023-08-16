package com.example.weatherapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import com.example.weatherapp.di.AppComponent
import com.example.weatherapp.di.DaggerAppComponent
import com.example.weatherapp.domain.Repository
import com.example.weatherapp.presentation.WeatherUiModel
import com.example.weatherapp.presentation.WeatherViewModel
import com.example.weatherapp.presentation.WeatherScreen
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var component: AppComponent

    @Inject
    protected lateinit var repository: Repository

    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModel.WeatherFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerAppComponent.factory().create(applicationContext).apply {
            inject(this@MainActivity)
        }


        setContent {
            WeatherScreen(
                searchQuery = viewModel.searchQuery.collectAsState().value,
                onValueChange = { viewModel.setTitle(it) },
                weatherState =viewModel.weatherForecast.collectAsState(initial = WeatherUiModel.Loading).value
            )
        }

    }
}