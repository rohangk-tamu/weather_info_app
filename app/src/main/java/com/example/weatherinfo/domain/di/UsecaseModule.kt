package com.example.weatherinfo.domain.di

import com.example.weatherinfo.data.repository.SearchRepoImpl
import com.example.weatherinfo.data.repository.WeatherRepoImpl
import com.example.weatherinfo.domain.usecases.DeleteCityUsecase
import com.example.weatherinfo.domain.usecases.GetCurrentWeather
import com.example.weatherinfo.domain.usecases.GetDailyWeather
import com.example.weatherinfo.domain.usecases.GetHourlyWeather
import com.example.weatherinfo.domain.usecases.GetSearchResults
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UsecaseModule {
    @Provides
    fun providegetCurrentWeather(weatherRepoImpl: WeatherRepoImpl): GetCurrentWeather {
        return GetCurrentWeather(weatherRepoImpl)
    }

    @Provides
    fun providegetDailyWeather(weatherRepoImpl: WeatherRepoImpl): GetDailyWeather {
        return GetDailyWeather(weatherRepoImpl)
    }


    @Provides
    fun provideHourlyWeather(weatherRepoImpl: WeatherRepoImpl): GetHourlyWeather {
        return GetHourlyWeather(weatherRepoImpl)
    }

    @Provides
    fun provideSearchResults(searchRepoImpl: SearchRepoImpl): GetSearchResults {
        return GetSearchResults(searchRepoImpl)
    }

    @Provides
    fun providedeleteusecas(searchRepoImpl: SearchRepoImpl): DeleteCityUsecase {
        return DeleteCityUsecase(searchRepoImpl)
    }


}