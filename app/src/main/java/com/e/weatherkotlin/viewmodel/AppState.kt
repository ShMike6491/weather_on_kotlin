package com.e.weatherkotlin.viewmodel

import com.e.weatherkotlin.model.WeatherModel

sealed class AppState {
    data class Success(val weatherData: WeatherModel) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}