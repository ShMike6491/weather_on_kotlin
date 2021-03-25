package com.e.weatherkotlin.model

interface Repository {
    fun getDataFromServer(): WeatherModel
    fun getDataFromCash(): WeatherModel
}