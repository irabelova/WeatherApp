package com.example.weatherapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.domain.DailyWeatherModel

@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onValueChange: (String) -> Unit,
    weatherState: WeatherUiModel
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SearchTopBar(
            searchQuery = searchQuery,
            onValueChange = onValueChange
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        ) {
            when (weatherState) {
                WeatherUiModel.Loading -> item {
                Box(modifier = Modifier.fillParentMaxSize()) {
                    LoadingScreen()
                }
                }
                is WeatherUiModel.Data -> {
                    item {
                        Text(
                            text = stringResource(
                                id = R.string.weather_in_the_city, weatherState.weather[0].city
                            ),
                            fontSize = 18.sp,
                            color = Color.Black,
                            maxLines = 1,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    items(weatherState.weather) { weather ->
                        DailyWeatherCard(
                            modifier = Modifier.padding(vertical = 8.dp),
                            date = weather.date,
                            avgTemp = weather.avgTemp,
                            maxWind = weather.maxWind,
                            avgHumidity = weather.avgHumidity,
                            text = weather.text,
                            icon = weather.icon
                        )
                    }
                }

                WeatherUiModel.Error -> item {
                    ErrorScreen()
                }

                WeatherUiModel.Empty -> item {
                    ErrorSearchScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    val dailyWeather1 = DailyWeatherModel(
        date = "2023-08-18",
        city = "Moscow",
        avgTemp = 29.8F,
        maxWind = 19.8F,
        avgHumidity = 31,
        text = "Patchy rain possible",
        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    )

    val dailyWeather2 = DailyWeatherModel(
        date = "2023-08-19",
        city = "Moscow",
        avgTemp = 30.8F,
        maxWind = 14.8F,
        avgHumidity = 32,
        text = "Sunny",
        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    )

    val dailyWeather3 = DailyWeatherModel(
        date = "2023-08-20",
        city = "Moscow",
        avgTemp = 20.8F,
        maxWind = 24.8F,
        avgHumidity = 22,
        text = "Rainy",
        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    )

    val weatherList = listOf(dailyWeather1, dailyWeather2, dailyWeather3)

    WeatherScreen(
        searchQuery = "Moscow",
        onValueChange = {},
        weatherState = WeatherUiModel.Data(weatherList)
    )
}