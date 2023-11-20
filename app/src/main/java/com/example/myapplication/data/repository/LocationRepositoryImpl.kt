package com.example.myapplication.data.repository

import javax.inject.Inject
import kotlin.Exception
import android.annotation.SuppressLint
import android.location.Location
import com.example.myapplication.domain.DataState
import com.example.myapplication.domain.extensions.LocationException
import com.example.myapplication.domain.repositories.LocationRepo
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class LocationRepositoryImpl @Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : LocationRepo {

    @ExperimentalCoroutinesApi
    @SuppressLint("MissingPermission")
    override fun getLocation(): Flow<DataState<Location>> =
        callbackFlow {
            try {
                with(
                    fusedLocationProviderClient.lastLocation
                ) {
                    addOnSuccessListener {
                        when (it) {
                            is Location -> this@callbackFlow.trySend(DataState.Success(it)).isSuccess
                            else -> this@callbackFlow.trySend(DataState.Error(LocationException())).isSuccess
                        }
                    }
                    addOnFailureListener {
                        this@callbackFlow.trySend(DataState.Error(it)).isSuccess
                    }
                }
                awaitClose {
                    cancel()
                }
            } catch (e: Exception) {
                this.trySend(DataState.Error(e)).isSuccess
            }
        }.flowOn(Dispatchers.Main)
}