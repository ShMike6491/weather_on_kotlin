package com.e.weatherkotlin.view.main

import com.e.weatherkotlin.model.WeatherModel

interface CallbackClickHandler {
    fun handleClick(model: WeatherModel);
}