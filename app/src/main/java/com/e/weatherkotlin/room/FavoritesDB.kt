package com.e.weatherkotlin.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.weatherkotlin.model.CityModel

@Database(entities = arrayOf(CityModel::class), version = 1, exportSchema = false)
abstract class FavoritesDB : RoomDatabase() {
    abstract fun dao(): FavoritesDAO
}