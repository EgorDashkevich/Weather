package com.example.myapplication.presenter.screen.menu

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.core.fragment.BaseFragment
import com.example.myapplication.databinding.FragmentMenuBinding

class MenuFragment : BaseFragment(R.layout.fragment_menu) {
    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            menuBottomNavigation.setupWithNavController(
                navController = (childFragmentManager.findFragmentById(R.id.menu_fragment_container) as NavHostFragment).navController
            )
        }
    }
}