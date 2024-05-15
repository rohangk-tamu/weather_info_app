package com.example.weatherinfo.domain.usecases

import com.example.weatherinfo.data.repository.SearchRepoImpl
import com.example.weatherinfo.data.searchmodel.CityEntity
import com.example.weatherinfo.domain.searchResultModel.Search_Results
import com.example.weatherinfo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetSearchResults  @Inject constructor(private val repoImpl: SearchRepoImpl){

    operator  fun invoke( cityname : String ): Flow<Resource<List<Search_Results>>> = flow {

        try {
            emit(Resource.Success(repoImpl.GetSearchResutls(cityname)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }

    }

      suspend operator fun invoke(city: CityEntity) {
       repoImpl.insertCity(city)
    }


    operator fun invoke(): Flow<List<CityEntity>> {
       return repoImpl.getAllCities()
    }

}