package com.example.weatherinfo.domain.repositroy

import com.example.weatherinfo.domain.model.CurrentWeather
import com.example.weatherinfo.domain.model.Daily
import com.example.weatherinfo.domain.model.Hourly
import java.time.DayOfWeek

interface WeatherRepo {

    suspend fun getCurrentWeather( lat : Double, lon : Double): CurrentWeather
    suspend fun getHourlyWeather( lat : Double, lon : Double): List<Hourly>
    suspend fun getDailyWeather( lat : Double, lon : Double): Map<DayOfWeek, List<Daily>>


}