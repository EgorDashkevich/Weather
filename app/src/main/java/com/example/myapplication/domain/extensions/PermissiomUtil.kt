package com.example.myapplication.domain.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

val REQUEST_PERMISSION_CODE = 53036

fun Context.checkPermissions() =
    checkCoarseLocationAllowed() or
            checkFineLocationAllowed()

fun Context.checkCoarseLocationAllowed() =
    ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

fun Context.checkFineLocationAllowed() =
    ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

fun Activity.askForPermissions() =
    ActivityCompat.requestPermissions(
        this,
        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
        REQUEST_PERMISSION_CODE
    )

fun necessaryPermissionsGranted(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) = requestCode == REQUEST_PERMISSION_CODE &&
        permissions.contains(Manifest.permission.ACCESS_COARSE_LOCATION) &&
        grantResults.firstOrNull() == PackageManager.PERMISSION_GRANTED