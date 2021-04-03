package com.e.weatherkotlin.view.details

import com.e.weatherkotlin.model.WeatherDTO

interface WeatherDataReceiver {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFailed(throwable: Throwable)
}