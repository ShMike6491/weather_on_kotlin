package com.e.weatherkotlin.model

interface Repository {
    fun getDataFromServer(): WeatherModel
    fun getDataFromCash(): WeatherModel
    fun getDataFromCashRus(): List<WeatherModel>
    fun getDataFromCashWorld(): List<WeatherModel>
}