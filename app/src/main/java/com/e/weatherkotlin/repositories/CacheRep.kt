package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.CityModel
import com.e.weatherkotlin.model.WeatherModel

interface CacheRep {
    fun getFavorites(): List<WeatherModel>
    fun saveToFavorites(model: CityModel)
    fun contains(model: CityModel): Boolean
    fun deleteFromFavorites(model: CityModel)
}