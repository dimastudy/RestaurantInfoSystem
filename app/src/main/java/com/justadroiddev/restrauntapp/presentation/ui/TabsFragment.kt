package com.justadroiddev.restrauntapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.FragmentTabsBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment

class TabsFragment : BaseFragment<FragmentTabsBinding>(FragmentTabsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.tabsContainer) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.bottomNavBar, navController)

    }



}

fun Fragment.findTopNavController() : NavController{
    val topLevelHost = requireActivity().supportFragmentManager.findFragmentById(R.id.activity_nav_host) as NavHostFragment?
    return topLevelHost?.navController ?: findNavController()
}