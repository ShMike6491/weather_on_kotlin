package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherDTO
import retrofit2.Callback

class DetailRepImpl(private val remoteResource: YandexAPI) : DetailsRep {
    override fun getWeather(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        remoteResource.getCityWeather(lat,lon, callback)
    }
}