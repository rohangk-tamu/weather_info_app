import com.example.weatherinfo.domain.location.LocationTracker
import com.example.weatherinfo.domain.usecases.GetDailyWeather
import com.example.weatherinfo.presentation.states.DailyweatherState
import com.example.weatherinfo.presentation.viewmodels.DailyweatherViewmodel
import com.example.weatherinfo.util.Resource
import io.mockk.coEvery
import io.mockk.every
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
class DailyweatherViewmodelTest {

    private lateinit var viewModel: DailyweatherViewmodel
    private lateinit var getDailyWeather: GetDailyWeather
    private lateinit var locationTracker: LocationTracker

    @Before
    fun setup() {
        getDailyWeather = mockk()
        locationTracker = mockk()
        viewModel = DailyweatherViewmodel(locationTracker, getDailyWeather)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after testing
        Dispatchers.resetMain()
    }
    @Test
    fun `fetchDailyWeather with invalid location should update state with error`() =
        runBlockingTest {
            // Given
            coEvery { locationTracker.getLocation() } returns null

            // When
            viewModel.fetchDailyWeather()

            // Then
            val expectedState = DailyweatherState(
                isLoading = false,
                data = null,
                error = "Location not available"
            )
            assertEquals(expectedState, viewModel.state)
        }
}
