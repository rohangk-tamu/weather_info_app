package com.example.weatherinfo.domain.usecases

import com.example.weatherinfo.data.repository.SearchRepoImpl
import com.example.weatherinfo.data.searchmodel.CityEntity
import javax.inject.Inject

class DeleteCityUsecase @Inject constructor(
   private val repoImpl: SearchRepoImpl
) {
    suspend operator fun invoke(city: CityEntity) {
        repoImpl.deleteCity(city)
    }
}