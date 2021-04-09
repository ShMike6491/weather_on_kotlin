package com.e.weatherkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherkotlin.repositories.DetailRepImpl
import com.e.weatherkotlin.repositories.DetailsRep

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRep = DetailRepImpl()
): ViewModel() {
    fun getLiveData() = liveData

    fun getWeatherInfo(lat: Double, lon: Double) {

    }
}