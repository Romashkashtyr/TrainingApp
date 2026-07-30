package com.example.trainings.ui

import androidx.fragment.app.Fragment
import com.example.trainings.ui.fragment_favorites.FavoritesFragment

object TrainingsScreens {

    fun favorites(): Fragment {
        return FavoritesFragment.newInstance()
    }
}