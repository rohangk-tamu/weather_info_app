import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weatherinfo.domain.location.LocationTracker
import com.example.weatherinfo.domain.usecases.GetCurrentWeather
import com.example.weatherinfo.presentation.states.CurrentWeatherState
import com.example.weatherinfo.presentation.viewmodels.CurrentWeatherViewModel

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrentWeatherViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CurrentWeatherViewModel
    private lateinit var getCurrentWeather: GetCurrentWeather
    private lateinit var locationTracker: LocationTracker

    @Before
    fun setup() {
        getCurrentWeather = mockk()
        locationTracker = mockk()
        viewModel = CurrentWeatherViewModel(mockk(), locationTracker, getCurrentWeather)

        // Inside the setup() method
        coEvery { locationTracker.getLocation() } returns mockk()

        coEvery { locationTracker.getLocation() } returns mockk {
            every { latitude } returns 10.0
            every { longitude } returns 20.0
        }

        // Set the main dispatcher for testing
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after testing
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCurrentWeather with invalid location should update state with error`() =
        runBlockingTest {
            // Given
            coEvery { locationTracker.getLocation() } returns null

            // When
            viewModel.fetchCurrentWeather()

            // Then
            val expectedState = CurrentWeatherState(
                isLoading = false,
                data = null,
                error = "Location not available"
            )
            assertEquals(expectedState, viewModel.state)
        }
}