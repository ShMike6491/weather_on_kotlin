package com.e.weatherkotlin.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class CityModel(
    val city: String,
    val lat: Double,
    val lon: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) : Parcelable