package com.example.weatherinfo.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getLocation() : Location ?
}