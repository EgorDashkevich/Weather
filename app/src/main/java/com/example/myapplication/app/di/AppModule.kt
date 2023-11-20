package com.example.myapplication.app.di

import com.example.myapplication.data.repository.WeatherRepositoryImpl
import com.example.myapplication.domain.repositories.WeatherRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepositoryImpl(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepo

}