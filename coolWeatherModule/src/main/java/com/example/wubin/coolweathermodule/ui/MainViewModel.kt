package com.example.wubin.coolweathermodule.ui

import androidx.lifecycle.ViewModel
import com.example.wubin.coolweathermodule.data.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) : ViewModel() {

    fun isWeatherCached() = repository.isWeatherCached()

}