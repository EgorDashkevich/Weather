package com.example.myapplication.presenter.screen.main.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.repositories.WeatherRepo
import com.example.myapplication.presenter.screen.main.viewholders.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsVM @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    var weather: LiveData<Weather> = MutableLiveData()


    fun getWeather(id: Long) {
        weather = weatherRepo.getWeather(id)
    }

}