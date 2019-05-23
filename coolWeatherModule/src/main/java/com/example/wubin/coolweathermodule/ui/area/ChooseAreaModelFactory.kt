package com.example.wubin.coolweathermodule.ui.area

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wubin.coolweathermodule.data.PlaceRepository

class ChooseAreaModelFactory(private val repository: PlaceRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChooseAreaViewModel(repository) as T
    }
}