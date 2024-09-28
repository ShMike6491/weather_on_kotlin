package com.e.weatherkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherkotlin.model.Repository
import com.e.weatherkotlin.model.RepositoryImp
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImp()
) : ViewModel() {

    fun getData(): LiveData<AppState> {
        return liveDataToObserve
    }

    fun getDataFromRemote() = getDataFromSource()

    fun getDataFromCash() = getDataFromSource()

    private fun getDataFromSource() {
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(AppState.Success(repositoryImpl.getDataFromCash()))
        }.start()
    }
}