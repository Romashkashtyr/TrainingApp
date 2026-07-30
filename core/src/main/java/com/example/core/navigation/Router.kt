package com.example.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Router {
    fun navigateTo(screen: Screen)

    fun navigateToFragment(
        screen: Screen,
        fragmentManager: FragmentManager,
    )

}