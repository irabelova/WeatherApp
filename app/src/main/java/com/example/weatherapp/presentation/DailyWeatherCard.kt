package com.example.weatherapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.weatherapp.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale


@Composable
fun DailyWeatherCard(
    modifier: Modifier,
    date: String,
    avgTemp: Float,
    maxWind: Float,
    avgHumidity: Int,
    text: String,
    icon: String
) {
    Surface(
        border = BorderStroke(
            1.dp,
            color = colorResource(id = R.color.grey)
        ),
        shadowElevation = 4.dp,
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        color = colorResource(id = R.color.card_background),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            val dateStr = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(
                Locale.ENGLISH).format(dateStr)
            Text(
                text = formattedDate,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )

            Row() {
                SubcomposeAsyncImage(
                    model = icon,
                    loading = { LoadingScreen() },
                    error = { ErrorImageScreen() },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Row() {
                        Text(
                            text = stringResource(
                                id = R.string.temperature,
                                String.format("%.1f", avgTemp)
                            ),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Column() {
                            Text(
                                text = stringResource(
                                    id = R.string.wind,
                                    String.format("%.1f", maxWind)
                                ),
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.grey),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.humidity, avgHumidity),
                                fontSize = 15.sp,
                                color = colorResource(id = R.color.grey),
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                    Text(
                        text = text,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DailyWeatherCardPreview() {
    DailyWeatherCard(
        modifier = Modifier.fillMaxWidth(),
        date = "2023-08-18",
        avgTemp = 29.8F,
        maxWind = 19.8F,
        avgHumidity = 31,
        text = "Patchy rain possible",
        icon = "//cdn.weatherapi.com/weather/64x64/day/116.png"
    )
}