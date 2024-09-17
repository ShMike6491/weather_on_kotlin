package com.e.weatherkotlin.utils

import com.e.weatherkotlin.model.CityModel
import com.e.weatherkotlin.model.WeatherDTO
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.model.getDefaultCity

fun convertDtoToModel(data: WeatherDTO): List<WeatherModel> {
    val fact = data.fact!!
    return listOf(WeatherModel(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon!!))
}

fun convertToWeatherList(listData: List<CityModel>): List<WeatherModel> {
    val weatherList = mutableListOf<WeatherModel>()
    for (city in listData) {
        val data = WeatherModel(city, 0, 0)
        weatherList.add(data)
    }
    return weatherList
}