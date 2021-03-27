package com.e.weatherkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    val city: CityModel = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
) : Parcelable

fun getDefaultCity() = CityModel("Moscow", 37.617299900000035, 55.755826)

fun getWorldCities(): List<WeatherModel> {
    return listOf(
        WeatherModel(CityModel("London", -0.1257400, 51.5085300), 15, 14),
        WeatherModel(CityModel("Paris", 34.1354145, 12.2130420), 19, 17),
        WeatherModel(CityModel("New-York", 65.2342356, 34.234614), 24, 26),
        WeatherModel(CityModel("Berlin", 19.3243240, 56.345345), 17, 15),
        WeatherModel(CityModel("Prague", 41.9027835, 21.412123), 15, 15),
        WeatherModel(CityModel("Sydney", 62.12343030, 10.543526), 27, 15)
        )
}

fun getRussianCities(): List<WeatherModel> {
    return listOf(
        WeatherModel(CityModel("Moscow", 37.617299900000035, 55.755826), 15, 14),
        WeatherModel(CityModel("Saint-Petersburg", 34.1354145, 12.2130420), 19, 17),
        WeatherModel(CityModel("Samara", 65.2342356, 34.234614), 24, 26),
        WeatherModel(CityModel("Khabarovsk", 19.3243240, 56.345345), 17, 15),
        WeatherModel(CityModel("Sochi", 41.9027835, 21.412123), 15, 15),
        WeatherModel(CityModel("Kalningrad", 62.12343030, 10.543526), 27, 15)
    )
}