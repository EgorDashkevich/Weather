package com.example.myapplication.data.repository

import android.location.Location
import com.example.myapplication.data.mapper.toWeathers
import com.example.myapplication.data.network.OpenWeatherMapService
import com.example.myapplication.domain.extensions.toTwoDigitsFormat
import com.example.myapplication.domain.repositories.WeatherDao
import com.example.myapplication.domain.repositories.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val openWeatherMapService: OpenWeatherMapService,
    private val weatherDao: WeatherDao
) : WeatherRepo {

    override suspend fun getForecast(forLocation: Location) {
        try {
            weatherDao.insertAll(withContext(Dispatchers.IO) {
                openWeatherMapService.getForecast(
                    latitude = forLocation.latitude.toTwoDigitsFormat(),
                    longitude = forLocation.longitude.toTwoDigitsFormat(),
                    appid = "3529dd6922f0ad43e77f1584d4a661dc",
                ).toWeathers()
            })
        } catch (e: Exception) {

        }
    }

    override fun getWeather(id: Long) = weatherDao.get(id)

    override fun getWeathers() = weatherDao.getAll()
}