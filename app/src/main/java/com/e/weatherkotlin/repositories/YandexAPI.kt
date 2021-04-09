package com.e.weatherkotlin.repositories

import com.e.weatherkotlin.BuildConfig
import com.e.weatherkotlin.model.WeatherDTO
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class YandexAPI {
    private val QUERY = "https://api.weather.yandex.ru/"

    private val weatherApi = Retrofit.Builder()
        .baseUrl(QUERY)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            )
        )
        .client(getClient(WeatherInterceptor()))
        .build()
        .create(WeatherAPI::class.java)

    fun getCityWeather(lat: Double, lon: Double, callback: Callback<WeatherDTO>) {
        weatherApi.getWeather(BuildConfig.WEATHER_API_KEY, lat, lon).enqueue(callback)
    }

    private fun getClient(interceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)
        client.addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
        return client.build()
    }

    inner class WeatherInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }
    }
}