package com.e.weatherkotlin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityModel(
    val city: String,
    val lat: Double,
    val lon: Double
) : Parcelable