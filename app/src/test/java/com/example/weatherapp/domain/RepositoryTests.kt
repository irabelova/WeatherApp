package com.example.weatherapp.domain

import com.example.weatherapp.dailyWeatherModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryTests {

    @Mock
    private lateinit var dataSource: DataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = Repository(dataSource, localDataSource)
    }

    @Test
    fun `observe weather forecast test`() = runTest {
        val localDataSourceResult = flow {
            emit(listOf(dailyWeatherModel))
        }
        Mockito.`when`(localDataSource.observeWeatherForecast())
            .thenReturn(localDataSourceResult)
        val repositoryResult = repository.observeWeatherForecast()
        assertEquals(localDataSourceResult, repositoryResult)
    }

    @Test
    fun `update forecast test`() = runTest {
        val city = "Mos"
        Mockito.`when`(dataSource.getWeatherForecast(city))
            .thenReturn(listOf(dailyWeatherModel))

        assertEquals(
            dailyWeatherModel.city,
            repository.updateWeather(city)
        )
        Mockito.verify(
            localDataSource,
            Mockito.times(1)
        )
            .saveWeatherForecast(dailyWeatherModel.city, listOf(dailyWeatherModel))
    }
}