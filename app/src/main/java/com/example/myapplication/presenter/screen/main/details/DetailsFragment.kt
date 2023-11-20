package com.example.myapplication.presenter.screen.main.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.core.fragment.BaseFragment
import com.example.myapplication.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val detailsViewModel: DetailsVM by viewModels()

    private val binding by viewBinding(FragmentDetailsBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getLong("id")?.let { detailsViewModel.getWeather(it) }
        with(binding) {
            detailsViewModel.weather.observe(viewLifecycleOwner) {
                tvDateInfo.text = it.date
                tvCloudInfo.text = "${it.cloud}${"%"}"
                tvPressureInfo.text = it.pressure
                tvHumidityInfo.text = it.humidity
                tvTemperatureMaxInfo.text = it.temperatureMax
                tvTemperatureMinInfo.text = it.temperatureMin
                tvSpeedInfo.text = it.speed
                btnBack.setOnClickListener { findNavController().navigateUp() }
            }
        }
    }

}