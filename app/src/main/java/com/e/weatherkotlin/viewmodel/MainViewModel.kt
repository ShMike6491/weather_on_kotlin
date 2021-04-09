package com.e.weatherkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherkotlin.repositories.MainRep
import com.e.weatherkotlin.repositories.MainRepImp
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepImpl: MainRep = MainRepImp()
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getDataFromCashRus() = getDataFromSource(true)

    fun getDataFromCashWorld() = getDataFromSource(false)

    private fun getDataFromSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(if (isRussian)
                mainRepImpl.getDataFromCashRus() else
                mainRepImpl.getDataFromCashWorld()))
        }.start()
    }
}