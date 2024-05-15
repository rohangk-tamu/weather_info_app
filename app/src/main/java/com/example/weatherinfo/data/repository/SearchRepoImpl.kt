package com.example.weatherinfo.data.repository

import android.util.Log
import com.example.weatherinfo.data.network_and_dao.CityDao
import com.example.weatherinfo.data.network_and_dao.SearchApi
import com.example.weatherinfo.data.searchmodel.CityEntity
import com.example.weatherinfo.domain.repositroy.ResultsRepo
import com.example.weatherinfo.domain.searchResultModel.Search_Results
import com.example.weatherinfo.util.SafeApiRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepoImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val cityDao: CityDao
): ResultsRepo, SafeApiRequest() {
    override suspend fun GetSearchResutls(cityName: String): List<Search_Results> {
          val searchResponse = safeApiRequest {
              Log.d("Repository", "Fetching search results")
              searchApi.GetSearchresults(cityName)
          }
         val result  = searchResponse.results.map {
              Search_Results(
                  latitude = it?.latitude,
                  longitude = it?.longitude,
                  name = it?.name,
                  population = it?.population,
                  country = it?.country,
                  timezone = it?.timezone,
                   admin1 = it?.admin1,

              )
         }
        return result
    }

    override suspend fun insertCity(city: CityEntity) {

       cityDao.insertCity(city)
    }

    override suspend fun deleteCity(city: CityEntity) {
        cityDao.deleteCity(city)
    }

    override fun getAllCities(): Flow<List<CityEntity>> {
     return cityDao.getAllCities()
    }


}