package com.example.weatherinfo.presentation.states

import com.example.weatherinfo.domain.searchResultModel.Search_Results

data class SearchResultsState (
    val isLoading : Boolean = false,
    val data : List<Search_Results>? = null,
    val error : String? = null
        )
