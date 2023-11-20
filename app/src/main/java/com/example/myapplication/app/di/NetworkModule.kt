package com.example.myapplication.app.di

import com.example.myapplication.data.network.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor =
        Interceptor {
            with(
                it.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                        "mode",
                        "json"
                    )
                    .build()
            ) {
                it.proceed(
                    it.request()
                        .newBuilder()
                        .url(this)
                        .build()
                )
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpclient(
        interceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideOpenWeatherMapService(
        retrofit: Retrofit
    ): OpenWeatherMapService =
        retrofit.create(OpenWeatherMapService::class.java)

}