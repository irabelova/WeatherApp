package com.example.weatherapp.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherFragmentBinding
import com.example.weatherapp.domain.Repository
import javax.inject.Inject

class WeatherFragment: Fragment() {
    private lateinit var binding: WeatherFragmentBinding

    @Inject
    protected lateinit var repository: Repository

    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModel.WeatherFactory(repository)
    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setTitle(p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                WeatherUiModel.Loading -> {
                    showWeatherLayout(false)
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                is WeatherUiModel.Data -> {
                    val adapter = WeatherAdapter(it.weather)
                    showWeatherLayout(true)
                    binding.recycleView.adapter = adapter
                    binding.weatherCity.text = binding.root.context.getString(R.string.weather_in_the_city, it.weather[0].city)
                }
                WeatherUiModel.Error -> {
                    showWeatherLayout(false)
                    binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                else -> {}
            }
        }
    }

    private fun showWeatherLayout(isShow: Boolean) {
        if (isShow){
            binding.statusContainer.visibility = View.GONE
            binding.weatherLayout.visibility = View.VISIBLE
        }
        else {
            binding.statusContainer.visibility = View.VISIBLE
            binding.weatherLayout.visibility = View.GONE
        }
    }
}