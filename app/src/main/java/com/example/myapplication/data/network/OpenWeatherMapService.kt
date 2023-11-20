package com.example.myapplication.data.network

import com.example.myapplication.data.dto.generated.ForecastDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("/data/2.5/forecast/")
    suspend fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("appid") appid: String,
    ): ForecastDTO
}