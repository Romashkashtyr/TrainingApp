package com.example.main

import androidx.fragment.app.Fragment
import com.example.core.navigation.Screen
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.ui.steps_fragment.StepsFragment
import com.example.main.ui.water_fragment.WaterFragment
import com.example.main.ui.workout_fragment.WorkoutFragment
import com.example.main.ui.workout_running.WorkoutRunningFragment

object MainScreens {

    fun getWaterFrag(): Fragment {
        return WaterFragment.newWaterInstance()
    }

    fun getStepsFrag(): Fragment {
        return StepsFragment.newStepsInstance()
    }

    fun getWorkoutRunningFrag(
        level: WorkoutLevel,
        type: WorkoutType
    ): Fragment {
        return WorkoutRunningFragment.newInstance(
            level = level,
            type = type
        )
    }

    fun getWorkoutFrag(): Fragment {
        return WorkoutFragment.newWorkoutInstance()
    }
}