package com.e.weatherkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherkotlin.App
import com.e.weatherkotlin.repositories.CacheRep
import com.e.weatherkotlin.repositories.CacheRepImpl

class FavoritesViewModel(
    val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: CacheRep = CacheRepImpl(App.favorites_dao)
) : ViewModel() {
    fun getData() = liveData

    fun getAllFavorites() {
        liveData.value = AppState.Loading
        liveData.value = AppState.Success(repository.getFavorites())
    }
}