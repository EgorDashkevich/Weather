package com.example.myapplication.presenter.extension

import android.app.Activity
import android.content.Intent
import com.example.myapplication.presenter.activity.MainActivity

fun Activity.navigateToMainScreen() {
    startActivity(
        Intent(
            this,
            MainActivity::class.java
        )
    )
    finish()
}