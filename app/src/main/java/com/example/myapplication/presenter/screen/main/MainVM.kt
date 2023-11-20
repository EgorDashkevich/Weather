package com.example.myapplication.presenter.screen.main

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.DataState
import com.example.myapplication.domain.repositories.LocationRepo
import com.example.myapplication.domain.repositories.WeatherRepo
import com.example.myapplication.presenter.screen.main.viewholders.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val weatherRepo: WeatherRepo,
    private val locationRepo: LocationRepo
) : ViewModel() {

    var weathers: LiveData<List<Weather>> = MutableLiveData()

    init {
        getLocation()
        weathers = weatherRepo.getWeathers()
    }

    private fun getLocation() {
        viewModelScope.launch {
            locationRepo.getLocation().collect {
                when (it) {
                    is DataState.Loading -> {
                    }

                    is DataState.Success -> {
                        getForecast(it.data)
                    }

                    is DataState.Error -> {
                    }
                }
            }
        }
    }

    private suspend fun getForecast(location: Location) {
        weatherRepo.getForecast(location)
    }

}