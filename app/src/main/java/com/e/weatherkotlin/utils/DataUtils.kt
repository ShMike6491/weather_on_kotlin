package com.e.weatherkotlin.utils

import com.e.weatherkotlin.model.WeatherDTO
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.model.getDefaultCity

fun convertDtoToModel(data: WeatherDTO): List<WeatherModel> {
    val fact = data.fact!!
    return listOf(WeatherModel(getDefaultCity(), fact.temp!!, fact.feels_like!!, fact.condition!!, fact.icon!!))
}