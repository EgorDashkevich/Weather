package com.example.myapplication.data.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.domain.repositories.WeatherDao
import com.example.myapplication.presenter.screen.main.viewholders.Weather


@Database(entities = [Weather::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}