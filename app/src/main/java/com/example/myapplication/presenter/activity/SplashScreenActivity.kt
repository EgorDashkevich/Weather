package com.example.myapplication.presenter.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.domain.extensions.askForPermissions
import com.example.myapplication.domain.extensions.checkPermissions
import com.example.myapplication.domain.extensions.necessaryPermissionsGranted
import com.example.myapplication.presenter.extension.navigateToMainScreen

class SplashScreenActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()

        if (checkPermissions()) {
            navigateToMainScreen()
        } else {
            askForPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (necessaryPermissionsGranted(requestCode, permissions, grantResults)) {
            navigateToMainScreen()
        } else {
            finish()
        }
    }
}