package com.e.weatherkotlin.view

import com.e.weatherkotlin.model.WeatherModel

interface CallbackClickHandler {
    fun handleClick(model: WeatherModel);
}