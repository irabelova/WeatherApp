package com.example.weatherapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.DailyWeatherForecastBinding
import com.example.weatherapp.domain.DailyWeatherModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

class WeatherAdapter(
    private val weatherForecastsList: List<DailyWeatherModel>
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {


    class WeatherViewHolder(
        private val binding: DailyWeatherForecastBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: DailyWeatherModel) {
            val date = LocalDate.parse(weather.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val formattedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(
                Locale.ENGLISH).format(date)
            binding.date.text = formattedDate
            binding.dailyWeatherDescription.text = weather.text
            binding.dailyHumidity.text =
                binding.root.context.getString(R.string.humidity, weather.avgHumidity)
            binding.dailyTemperature.text =
                binding.root.context.getString(R.string.temperature, String.format("%.1f", weather.avgTemp))
            binding.dailyWind.text = binding.root.context.getString(R.string.wind, String.format("%.1f", weather.maxWind))
            weather.icon.let {
                binding.dailyWeatherIcon.load(it) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return weatherForecastsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return WeatherViewHolder(
            DailyWeatherForecastBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val item = weatherForecastsList[position]
        holder.bind(item)
    }
}