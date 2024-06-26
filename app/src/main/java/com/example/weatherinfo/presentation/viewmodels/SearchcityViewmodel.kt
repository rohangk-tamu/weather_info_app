package com.example.weatherinfo.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherinfo.data.searchmodel.CityEntity
import com.example.weatherinfo.domain.usecases.DeleteCityUsecase
import com.example.weatherinfo.domain.usecases.GetSearchResults
import com.example.weatherinfo.presentation.states.SearchResultsState
import com.example.weatherinfo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchcityViewmodel @Inject constructor(
    private val getSearchResults: GetSearchResults,
    private  val deleteCityUsecase: DeleteCityUsecase,


    ) : ViewModel() {

     private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _state = MutableStateFlow(SearchResultsState())
    var state = _state.asStateFlow()

    var selectedCity = mutableStateOf<CityEntity?>(null)
        private set

    var switchState by mutableStateOf(true)
        internal set

    var  selectedLatitude = mutableStateOf<Double?>(null)
       private set

    var  selectedLongitude = mutableStateOf<Double?>(null)
    private set

    fun onSearchTextChange(text: String) {
        _searchText.value = text
        fetchSearchResults(text)
    }

    fun toggleSwitchState(b: Boolean) {
        selectedLongitude.value = null
        selectedLatitude.value = null
     switchState = b

 }

    private fun fetchSearchResults(query: String) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(data = null, isLoading = true, error = null)
                val searchResultsFlow = getSearchResults(query)
                searchResultsFlow.collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            val data = resource.data

                            _state.value =
                                _state.value.copy(data = data, isLoading = false, error = null)
                            Log.d("ViewModel4", "search results fetched successfully: $data")

                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                data = null,
                                isLoading = false,
                                error = "can't fetch"
                            )
                            Log.e("ViewModel4A", "Error fetching weather: ${resource.message}")
                        }


                    }
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(data = null, isLoading = false, error = e.message)

            }
        }

    }

    fun insertCity(city: CityEntity) {
        viewModelScope.launch {
          getSearchResults(city) //it will trigger insert fun in usecase because it takes cityentity
        }
    }

    // Delete a city
    fun deleteCity(city: CityEntity) {
        viewModelScope.launch {
           deleteCityUsecase(city)
        }
    }


    val allCities: Flow<List<CityEntity>> = getSearchResults()
}