package com.example.wubin.coolweathermodule.util


import com.example.wubin.coolweathermodule.data.PlaceRepository
import com.example.wubin.coolweathermodule.data.WeatherRepository
import com.example.wubin.coolweathermodule.data.db.CoolWeatherDatabase
import com.example.wubin.coolweathermodule.data.network.CoolWeatherNetwork
import com.example.wubin.coolweathermodule.ui.MainModelFactory
import com.example.wubin.coolweathermodule.ui.area.ChooseAreaModelFactory
import com.example.wubin.coolweathermodule.ui.weather.WeatherModelFactory

object InjectorUtil {

    private fun getPlaceRepository() = PlaceRepository.getInstance(CoolWeatherDatabase.getPlaceDao(), CoolWeatherNetwork.getInstance())

    private fun getWeatherRepository() = WeatherRepository.getInstance(CoolWeatherDatabase.getWeatherDao(), CoolWeatherNetwork.getInstance())

    fun getChooseAreaModelFactory() = ChooseAreaModelFactory(getPlaceRepository())

    fun getWeatherModelFactory() = WeatherModelFactory(getWeatherRepository())

    fun getMainModelFactory() = MainModelFactory(getWeatherRepository())

}