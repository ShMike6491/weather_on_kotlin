package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherDTO

class DetailRepImpl(private val remoteResource: YandexAPI) : DetailsRep {
    override fun getWeather(lat: Double, lon: Double, callback: retrofit2.Callback<WeatherDTO>) {
        remoteResource.getCityWeather(lat,lon, callback)
    }
}