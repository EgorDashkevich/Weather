package com.example.myapplication.domain.repositories

import android.location.Location
import androidx.lifecycle.LiveData
import com.example.myapplication.presenter.screen.main.viewholders.Weather

interface WeatherRepo {

    suspend fun getForecast(forLocation: Location)
    fun getWeather(id: Long): LiveData<Weather>
    fun getWeathers(): LiveData<List<Weather>>
}