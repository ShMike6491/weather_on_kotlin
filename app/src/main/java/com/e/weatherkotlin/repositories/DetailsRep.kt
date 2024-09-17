package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherDTO

interface DetailsRep {
    fun getWeather(
        lat: Double,
        lon: Double,
        callback: retrofit2.Callback<WeatherDTO>
    )
}