package com.e.weatherkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.e.weatherkotlin.App
import com.e.weatherkotlin.model.CityModel
import com.e.weatherkotlin.model.WeatherDTO
import com.e.weatherkotlin.model.WeatherModel
import com.e.weatherkotlin.repositories.*
import com.e.weatherkotlin.utils.convertDtoToModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val SERVER_ERROR = "server error"
private const val CORRUPTED_DATA = "the request data isn't valid"
private const val REQUEST_ERROR = "request error"

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRep = DetailRepImpl(YandexAPI()),
    private val favoritesRepository: CacheRep = CacheRepImpl(App.favorites_dao)
): ViewModel() {
    fun getLiveData() = liveData

    fun getWeatherInfo(lat: Double, lon: Double) {
        liveData.value = AppState.Loading
        repository.getWeather(lat, lon, callback)
    }

    fun saveCityToDB(model: CityModel) {
        favoritesRepository.saveToFavorites(model)
    }

    private val callback = object : Callback<WeatherDTO> {
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            val res: WeatherDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful && res != null) {
                    checkResponse(res)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }

        private fun checkResponse(res: WeatherDTO): AppState {
            val fact = res.fact
            return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
                AppState.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppState.Success(convertDtoToModel(res))
            }
        }
    }
}