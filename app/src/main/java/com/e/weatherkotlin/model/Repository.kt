package com.e.weatherkotlin.model

interface Repository {
    fun getWeatherFromServer(): WeatherModel
    fun getWeatherFromCash(): WeatherModel
}