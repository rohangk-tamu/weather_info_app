package com.example.weatherinfo.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherinfo.presentation.viewmodels.CurrentWeatherViewModel
import com.example.weatherinfo.presentation.composables.AllWeatherComposable
import com.example.weatherinfo.presentation.composables.LocationList
import com.example.weatherinfo.presentation.composables.SearchLocation
import com.example.weatherinfo.presentation.viewmodels.DailyweatherViewmodel
import com.example.weatherinfo.presentation.viewmodels.HourlyWeatherViewmodel
import com.example.weatherinfo.presentation.viewmodels.SearchcityViewmodel

@SuppressLint("RememberReturnType", "SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(   ) {

val navController = rememberNavController()
    val savedStateHandle = remember {
        SavedStateHandle()
    }

    val searchcityViewmodel: SearchcityViewmodel = hiltViewModel()
   val  currentWeatherViewModel: CurrentWeatherViewModel = hiltViewModel()
    val dailyweatherViewmodel: DailyweatherViewmodel = hiltViewModel()
   val  hourlyWeatherViewmodel: HourlyWeatherViewmodel = hiltViewModel()

    NavHost(navController = navController, startDestination = NavScrren.Home.route){

  composable(NavScrren.Home.route){ backStackEntry->
   AllWeatherComposable(navController,searchcityViewmodel= searchcityViewmodel, handle = savedStateHandle
            , viewModel = currentWeatherViewModel, dailyweatherViewmodel = dailyweatherViewmodel,
             hourlyWeatherViewmodel = hourlyWeatherViewmodel)
       }

      composable(NavScrren.Locations.route){ backStackEntry->
      LocationList(handle = savedStateHandle, navController = navController, searchcityViewmodel = searchcityViewmodel)

             }


     composable(NavScrren.Search.route){ backStackEntry->
         SearchLocation(navController,searchcityViewmodel)
  }

 }



}

