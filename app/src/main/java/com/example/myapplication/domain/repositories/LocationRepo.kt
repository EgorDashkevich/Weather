package com.example.myapplication.domain.repositories

import android.location.Location
import com.example.myapplication.domain.DataState
import kotlinx.coroutines.flow.Flow

interface LocationRepo {
    fun getLocation(): Flow<DataState<Location>>
}