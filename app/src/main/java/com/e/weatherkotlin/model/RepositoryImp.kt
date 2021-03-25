package com.e.weatherkotlin.model

class RepositoryImp : Repository {
    override fun getWeatherFromServer(): WeatherModel {
        return WeatherModel()
    }

    override fun getWeatherFromCash(): WeatherModel {
        return WeatherModel()
    }
}