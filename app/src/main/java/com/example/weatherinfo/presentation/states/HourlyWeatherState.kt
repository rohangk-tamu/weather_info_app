package com.example.weatherinfo.presentation.states

import com.example.weatherinfo.domain.model.Hourly

data class HourlyWeatherState(
    val isLoading: Boolean = false,
    val data: List<Hourly>? = null,
    val error: String? = null
)
