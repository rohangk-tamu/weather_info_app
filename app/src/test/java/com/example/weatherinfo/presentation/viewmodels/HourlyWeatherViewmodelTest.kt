package com.example.weatherinfo.presentation.viewmodels

import com.example.weatherinfo.data.location.DefaultLocationTracker
import com.example.weatherinfo.domain.usecases.GetHourlyWeather
import com.example.weatherinfo.presentation.states.HourlyWeatherState
import com.example.weatherinfo.util.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HourlyWeatherViewModelTest {

    private lateinit var viewModel: HourlyWeatherViewmodel
    private lateinit var getHourlyWeather: GetHourlyWeather
    private lateinit var locationTracker: DefaultLocationTracker

    @Before
    fun setup() {
        getHourlyWeather = mockk()
        locationTracker = mockk()
        viewModel = HourlyWeatherViewmodel(getHourlyWeather, locationTracker)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after testing
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchHourlyWeather with invalid location should update state with error`() = runBlockingTest {
        // Given
        coEvery { locationTracker.getLocation() } returns null

        // When
        viewModel.fetchHourlyWeather()

        // Then
        val expectedState = HourlyWeatherState(
            isLoading = false,
            data = null,
            error = "Location not available"
        )
        assertEquals(expectedState, viewModel.state)
    }
}
