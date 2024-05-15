package com.example.weatherinfo.data.searchmodel

import com.example.weatherinfo.data.searchmodel.Result_Dto

data class SearchResults_Dto(
    val generationtime_ms: Double,
    val results: List<Result_Dto>
)