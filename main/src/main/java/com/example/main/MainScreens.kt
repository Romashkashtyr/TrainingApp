package com.example.main

import androidx.fragment.app.Fragment
import com.example.main.ui.steps_fragment.StepsFragment
import com.example.main.ui.water_fragment.WaterFragment

object MainScreens {

    fun getWaterFrag(): Fragment {
        return WaterFragment.newWaterInstance()
    }

    fun getStepsFrag(): Fragment {
        return StepsFragment.newStepsInstance()
    }
}