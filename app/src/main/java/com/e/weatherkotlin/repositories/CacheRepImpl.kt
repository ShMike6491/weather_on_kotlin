package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.CityModel
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.room.FavoritesDAO
import com.e.weatherkotlin.utils.convertToWeatherList

class CacheRepImpl (private val localSource: FavoritesDAO) : CacheRep {
    override fun getFavorites(): List<WeatherModel> {
        return convertToWeatherList(localSource.all())
    }

    override fun saveToFavorites(model: CityModel) {
        localSource.insert(model)
    }

    override fun contains(model: CityModel): Boolean {
        val entireList = localSource.all()
        return entireList.contains(model)
    }

    override fun deleteFromFavorites(model: CityModel) {
        localSource.delete(model)
    }
}