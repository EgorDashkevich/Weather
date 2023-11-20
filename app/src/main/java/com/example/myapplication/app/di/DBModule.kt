package com.example.myapplication.app.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.repository.AppDatabase
import com.example.myapplication.domain.repositories.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "weather_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): WeatherDao {
        return database.weatherDao()
    }
}