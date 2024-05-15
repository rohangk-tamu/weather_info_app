package com.example.weatherinfo.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.weatherinfo.data.repository.WeatherRepoImpl
import com.example.weatherinfo.domain.model.Hourly
import com.example.weatherinfo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHourlyWeather @Inject constructor(
    private val weatherRepoImpl: WeatherRepoImpl
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun  invoke(lat:Double, lon:Double) :Flow<Resource<List<Hourly>>> = flow{
        try {
            emit(Resource.Success(weatherRepoImpl.getHourlyWeather(lat , lon)))
        }catch (e:Exception){
            emit(Resource.Error(e.message))
        }
    }
}