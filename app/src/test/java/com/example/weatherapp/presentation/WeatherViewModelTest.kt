package com.example.weatherapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherapp.dailyWeatherModel
import com.example.weatherapp.domain.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @Mock
    private lateinit var repository: Repository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createViewModel() = WeatherViewModel(repository)

    @Test
    fun `initial state flow throws error`() = runTest {
        `when`(repository.observeWeatherForecast()).thenReturn(
            flow {
               throw RuntimeException()
            }
        )
        val viewModel = createViewModel()
        assertEquals(WeatherUiModel.Error, viewModel.weatherForecast.first())
    }

    @Test
    fun `initial state flow return empty list`() = runTest {
        `when`(repository.observeWeatherForecast()).thenReturn(
            flow {
                emit(emptyList())
            }
        )
        val viewModel = createViewModel()
        assertEquals(WeatherUiModel.Empty, viewModel.weatherForecast.first())
    }

    @Test
    fun `initial state flow return data with required city`() = runTest {
        `when`(repository.observeWeatherForecast()).thenReturn(
            flow {
                emit(listOf(dailyWeatherModel))
            }
        )
        val viewModel = createViewModel()
        assertEquals(
            WeatherUiModel.Data(listOf(dailyWeatherModel)),
            viewModel.weatherForecast.first()
        )
    }

    @Test
    fun `initial state flow return data with wrong city`() = runTest {
        `when`(repository.observeWeatherForecast()).thenReturn(
            flow {
                emit(listOf(dailyWeatherModel.copy(city = "WRONG")))
            }
        )
        val viewModel = createViewModel()
        assertEquals(WeatherUiModel.Empty, viewModel.weatherForecast.first())
    }

    @Test
    fun `search should change filter`() = runTest {
        val city = "WRONG"
        `when`(repository.observeWeatherForecast()).thenReturn(
            flow {
                emit(listOf(dailyWeatherModel.copy(city = city)))
            }
        )
        val viewModel = createViewModel()
        assertEquals( WeatherUiModel.Empty, viewModel.weatherForecast.first())
        viewModel.setTitle(city)
        //delay because of input debounce
        delay(400L)
        assertEquals( WeatherUiModel.Data(listOf(dailyWeatherModel.copy(city = city))), viewModel.weatherForecast.first())
        verify(repository, times(1)).updateWeather(city)

    }
}
