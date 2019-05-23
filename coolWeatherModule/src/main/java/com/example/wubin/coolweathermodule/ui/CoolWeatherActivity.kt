package com.example.wubin.coolweathermodule.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.wubin.baselibrary.activity.BaseActivity
import com.example.wubin.coolweathermodule.R
import com.example.wubin.coolweathermodule.ui.area.ChooseAreaFragment
import com.example.wubin.coolweathermodule.ui.weather.WeatherActivity
import com.example.wubin.coolweathermodule.util.InjectorUtil

class CoolWeatherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cool_weather)

        val viewModel = ViewModelProviders.of(this, InjectorUtil.getMainModelFactory()).get(MainViewModel::class.java)
        if (viewModel.isWeatherCached()) {
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container, ChooseAreaFragment()).commit()
        }
    }

}
