package com.example.weatherinfo.data.network_and_dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherinfo.data.searchmodel.CityEntity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao
}

