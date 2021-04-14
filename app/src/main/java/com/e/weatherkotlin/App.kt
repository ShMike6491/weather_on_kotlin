package com.e.weatherkotlin

import android.app.Application
import androidx.room.Room
import com.e.weatherkotlin.room.FavoritesDB

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: App? = null
        private var db: FavoritesDB? = null
        private const val DB_NAME = "Favorites.db"

        val favorites_dao by lazy(LazyThreadSafetyMode.NONE) {
            Room.databaseBuilder(
                appInstance!!.applicationContext,
                FavoritesDB::class.java,
                DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .dao()
        }


        //оставил для референса
//        fun getFavoritesDao(): FavoritesDAO {
//            if (db == null) {
//                synchronized(FavoritesDB::class.java) {
//                    if (db == null) {
//                        if (appInstance == null) throw
//                        IllegalStateException("Application is null while creating DataBase")
//
//                        db = Room.databaseBuilder(
//                            appInstance!!.applicationContext,
//                            FavoritesDB::class.java,
//                            DB_NAME)
//                            .allowMainThreadQueries()
//                            .build()
//                    }
//                }
//            }
//            return db!!.dao()
//        }
    }
}