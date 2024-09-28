package com.e.weatherkotlin.model

data class WeatherModel(
    val city: CityModel = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultCity() = CityModel("Moscow", 37.617299900000035, 55.755826)