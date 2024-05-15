package com.example.weatherinfo.data.model

data class CurrentWeather_Dto(
    val is_day: Int,
    val temperature: Double,
    val time: String,
    val weathercode: Int,
    val winddirection: Int,
    val windspeed: Double
)