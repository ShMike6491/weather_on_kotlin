package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.model.getRussianCities
import com.e.weatherkotlin.model.getWorldCities

class MainRepImp : MainRep {
    override fun getDataFromServer(): WeatherModel {
        return WeatherModel()
    }

    override fun getDataFromCash(): WeatherModel {
        return WeatherModel()
    }

    override fun getDataFromCashRus(): List<WeatherModel> {
        return getRussianCities()
    }

    override fun getDataFromCashWorld(): List<WeatherModel> {
        return getWorldCities()
    }
}