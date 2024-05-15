package com.example.weatherinfo.presentation.states

import com.example.weatherinfo.domain.model.CurrentWeather

data class CurrentWeatherState(
    val isLoading : Boolean = false,
    val data : CurrentWeather? = null,
    val error: String? = null

)
