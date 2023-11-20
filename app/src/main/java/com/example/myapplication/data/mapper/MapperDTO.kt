package com.example.myapplication.data.mapper

import com.example.myapplication.data.dto.generated.ForecastDTO
import com.example.myapplication.domain.extensions.kelvinToCelsius
import com.example.myapplication.presenter.screen.main.viewholders.Weather

fun ForecastDTO.toWeathers(): List<Weather> {
    return list.map {
        Weather(
            date = it.dtTxt,
            cloud = it.clouds.all,
            humidity = it.main.humidity,
            pressure = it.main.pressure,
            temperatureMax = it.main.tempMax.kelvinToCelsius().toInt().toString(),
            temperatureMin = it.main.tempMin.kelvinToCelsius().toInt().toString(),
            speed = it.wind.speed.toString(),
            id = it.dt
        )
    }
}