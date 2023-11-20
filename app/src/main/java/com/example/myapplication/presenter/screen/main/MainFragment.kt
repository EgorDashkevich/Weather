package com.example.myapplication.presenter.screen.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.core.adapter.lazyAdapter
import com.example.myapplication.core.fragment.BaseFragment
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.presenter.screen.main.viewholders.weatherViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val mainViewModel: MainVM by viewModels()

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val adapter by lazyAdapter(
        weatherViewHolder(
            ::onClick
        )
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvWeather.adapter = adapter
            mainViewModel.weathers.observe(viewLifecycleOwner) {
                adapter.updateItems(it)
            }
        }
    }

    private fun onClick(id: Long) {
        requireActivity().findNavController(R.id.fragment_container).navigate(
            R.id.detailsFragment,
            bundleOf("id" to id)
        )
    }

}