package com.example.weatherinfo.domain.searchResultModel

import com.example.weatherinfo.data.searchmodel.CityEntity

object Mapper2 {

    fun maptoCityEntity(searchResult: Search_Results): CityEntity {
        return CityEntity(

            name = searchResult.name,
            population = searchResult.population,
            timezone = searchResult.timezone,
            country = searchResult.country,
            admin1 = searchResult.admin1,
            latitude = searchResult.latitude,
            longitude = searchResult.longitude
        )
    }

}