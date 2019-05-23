package com.example.wubin.coolweathermodule.data.db

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.coolweathermodule.data.model.weather.Weather
import com.google.gson.Gson

class WeatherDao {

    fun cacheWeatherInfo(weather: Weather?) {
        if (weather == null) return
        PreferenceManager.getDefaultSharedPreferences(BaseActivity.myActivity.applicationContext).edit {
            val weatherInfo = Gson().toJson(weather)
            putString("weather", weatherInfo)
        }
    }

    fun getCachedWeatherInfo(): Weather? {
        val weatherInfo = PreferenceManager.getDefaultSharedPreferences(BaseActivity.myActivity.applicationContext).getString("weather", null)
        if (weatherInfo != null) {
            return Gson().fromJson(weatherInfo, Weather::class.java)
        }
        return null
    }

    fun cacheBingPic(bingPic: String?) {
        if (bingPic == null) return
        PreferenceManager.getDefaultSharedPreferences(BaseActivity.myActivity.applicationContext).edit {
            putString("bing_pic", bingPic)
        }
    }

    fun getCachedBingPic(): String? = PreferenceManager.getDefaultSharedPreferences(BaseActivity.myActivity.applicationContext).getString("bing_pic", null)

    private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        action(editor)
        editor.apply()
    }

}