package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherDTO
import retrofit2.Callback

class DetailRepImpl : DetailsRep {
    override fun getWeather(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        TODO("Not yet implemented")
    }
}