package com.example.myapplication.presenter.activity

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity(R.layout.activity_main) {

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        findNavController(R.id.fragment_container).navigateUp()
    }

}