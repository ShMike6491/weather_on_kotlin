package com.e.weatherkotlin.room

import androidx.room.*
import com.e.weatherkotlin.model.CityModel

@Dao
interface FavoritesDAO {
    @Query("SELECT * FROM CityModel")
    fun all(): List<CityModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: CityModel)

    @Delete
    fun delete(entity: CityModel)
}