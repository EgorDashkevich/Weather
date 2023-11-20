package com.example.myapplication.core.extensions

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun dateFormat(pattern: String): DateFormat = SimpleDateFormat(pattern, Locale.getDefault()).apply {
    this.timeZone =
        TimeZone.getTimeZone("UTC")
}