package com.e.weatherkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherModel(
    val city: CityModel = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val condition: String = "sunny",
    val icon: String? = "bkn_n"
) : Parcelable

fun getDefaultCity() = CityModel("Moscow", 37.617299900000035, 55.755826)

fun getWorldCities(): List<WeatherModel> {
    return listOf(
        WeatherModel(CityModel("Лондон", 51.5085300, -0.1257400), 1, 2),
        WeatherModel(CityModel("Токио", 35.6895000, 139.6917100), 3, 4),
        WeatherModel(CityModel("Париж", 48.8534100, 2.3488000), 5, 6),
        WeatherModel(CityModel("Берлин", 52.52000659999999, 13.404953999999975), 7, 8),
        WeatherModel(CityModel("Рим", 41.9027835, 12.496365500000024), 9, 10),
        WeatherModel(CityModel("Минск", 53.90453979999999, 27.561524400000053), 11, 12),
        WeatherModel(CityModel("Стамбул", 41.0082376, 28.97835889999999), 13, 14),
        WeatherModel(CityModel("Вашингтон", 38.9071923, -77.03687070000001), 15, 16),
        WeatherModel(CityModel("Киев", 50.4501, 30.523400000000038), 17, 18),
        WeatherModel(CityModel("Пекин", 39.90419989999999, 116.40739630000007), 19, 20)
        )
}

fun getRussianCities(): List<WeatherModel> {
    return listOf(
        WeatherModel(CityModel("Москва", 55.755826, 37.617299900000035), 1, 2),
        WeatherModel(CityModel("Санкт-Петербург", 59.9342802, 30.335098600000038), 3, 3),
        WeatherModel(CityModel("Новосибирск", 55.00835259999999, 82.93573270000002), 5, 6),
        WeatherModel(CityModel("Екатеринбург", 56.83892609999999, 60.60570250000001), 7, 8),
        WeatherModel(CityModel("Нижний Новгород", 56.2965039, 43.936059), 9, 10),
        WeatherModel(CityModel("Казань", 55.8304307, 49.06608060000008), 11, 12),
        WeatherModel(CityModel("Челябинск", 55.1644419, 61.4368432), 13, 14),
        WeatherModel(CityModel("Омск", 54.9884804, 73.32423610000001), 15, 16),
        WeatherModel(CityModel("Ростов-на-Дону", 47.2357137, 39.701505), 17, 18),
        WeatherModel(CityModel("Уфа", 54.7387621, 55.972055400000045), 19, 20)
    )
}