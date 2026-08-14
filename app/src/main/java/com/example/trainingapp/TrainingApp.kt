package com.example.trainingapp

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.auth.di.AuthComponent
import com.example.auth.ui.AuthorizationActivity
import com.example.core.di.CoreComponent
import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.main.di.MainComponent
import com.example.main.ui.activity.MainActivity
import com.example.splash.di.SplashComponent
import com.example.database.TrainingRoomDatabase
import com.example.main.MainScreens
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.trainings.di.TrainingComponent
import com.example.trainings.ui.TrainingsScreens
import com.example.trainings.ui.training_activity.TrainingsListActivity
import com.google.firebase.FirebaseApp

class TrainingApp : Application(), Router {


    override fun onCreate() {
        super.onCreate()
        instance = this
        initDi()
        FirebaseApp.initializeApp(this)
        TrainingRoomDatabase.initializeDb(this)
        RouterHolder.router = this
    }

    private fun initDi() {
        val coreComponent = CoreComponent.init(applicationContext)
        val trainingComponent = TrainingComponent.init(coreComponent)
        val exercisesProvider = trainingComponent.exercisesProvider()
        MainComponent.init(coreComponent,exercisesProvider)
        SplashComponent.init(coreComponent)
        AuthComponent.init(coreComponent)
        TrainingComponent.init(coreComponent)
    }


    override fun navigateTo(screen: Screen) {
        when (screen) {
            is Screen.Main -> screen.fromContext.startActivity(MainActivity.getIntent(screen.fromContext))
            is Screen.TrainingNav -> screen.fromContext.startActivity(
                TrainingsListActivity.getIntent(
                    screen.fromContext
                )
            )

            is Screen.Auth -> screen.fromContext.startActivity(
                AuthorizationActivity.getIntent(
                    screen.fromContext
                )
            )

            is Screen.TrainingFav -> {}
//                screen.fromContext.startActivity(
//                MainActivity.getIntent(
//                    screen.fromContext
//                )
//            )
            else -> Unit

        }
    }

    override fun navigateToFragment(
        screen: Screen,
        fragmentManager: FragmentManager,
    ) {
        Log.d("WATER_NAV", "navigateToFragment: $screen")
        when (screen) {
            is Screen.TrainingFav ->  {
                fragmentManager.beginTransaction()
                    .replace(
                        screen.containerId,
                        TrainingsScreens.favorites()
                    )
                    .addToBackStack(null)
                    .commit()
            }
            is Screen.WaterFrag -> {
                fragmentManager.beginTransaction()
                    .replace(
                        screen.containerId,
                        MainScreens.getWaterFrag()
                    )
                    .addToBackStack(null)
                    .commit()
            }
            is Screen.StepsFragmentRoute -> {
                fragmentManager.beginTransaction()
                    .replace(
                        screen.containerId,
                        MainScreens.getStepsFrag()
                    )
                    .addToBackStack(null)
                    .commit()
            }
            is Screen.WorkoutRunningFragmentRoute -> {
                val level = try {

                    WorkoutLevel.valueOf(
                        screen.level
                    )

                } catch (e: IllegalArgumentException) {

                    WorkoutLevel.EASY
                }

                val type = try {

                    WorkoutType.valueOf(
                        screen.type
                    )

                } catch (e: IllegalArgumentException) {

                    WorkoutType.FULL_BODY
                }

                val fragment =
                    MainScreens.getWorkoutRunningFrag(
                        level = level,
                        type = type
                    )

                fragmentManager
                    .beginTransaction()
                    .replace(
                        screen.containerId,
                        fragment
                    )
                    .addToBackStack(null)
                    .commit()
            }
            else -> Unit
        }
    }

    override fun navigateToFragment(
        fragment: Fragment,
        containerId: Int,
        fragmentManager: FragmentManager
    ) {
        fragmentManager
            .beginTransaction()
            .replace(
                containerId,
                fragment
            )
            .addToBackStack(null)
            .commit()
    }

    companion object {
        lateinit var instance: Application
    }

}