package com.e.weatherkotlin.model

class RepositoryImp : Repository {
    override fun getDataFromServer(): WeatherModel {
        return WeatherModel()
    }

    override fun getDataFromCash(): WeatherModel {
        return WeatherModel()
    }
}