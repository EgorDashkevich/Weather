package com.example.myapplication.domain.extensions

import java.util.Calendar
import java.util.Locale

fun Double.kelvinToCelsius() =
    this - 273

fun Double.format(digits: Int) =
    "%.${digits}f".format(
        Locale.US,
        this
    )

fun Double.toTwoDigitsFormat() =
    format(2)

fun Calendar.day() =
    get(Calendar.DAY_OF_YEAR)



