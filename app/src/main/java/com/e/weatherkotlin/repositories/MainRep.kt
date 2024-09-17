package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherModel

interface MainRep {
    fun getDataFromServer(): WeatherModel
    fun getDataFromCash(): WeatherModel
    fun getDataFromCashRus(): List<WeatherModel>
    fun getDataFromCashWorld(): List<WeatherModel>
}