package com.e.weatherkotlin.model

class RepositoryImp : Repository {
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