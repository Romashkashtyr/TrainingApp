package com.example.trainingapp

import android.app.Application
import com.example.auth.di.AuthComponent
import com.example.auth.ui.AuthorizationActivity
import com.example.core.di.CoreComponent
import com.example.core.navigation.Router
import com.example.core.navigation.RouterHolder
import com.example.core.navigation.Screen
import com.example.main.di.MainComponent
import com.example.main.ui.MainActivity
import com.example.splash.di.SplashComponent
import com.example.core.database.TrainingRoomDatabase
import com.example.trainings.di.TrainingComponent
import com.example.trainings.ui.fragment_detail_training.ExerciseDetailFragment
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
        MainComponent.init(coreComponent)
        SplashComponent.init(coreComponent)
        AuthComponent.init(coreComponent)
        TrainingComponent.init(coreComponent)
    }

    companion object {

        lateinit var instance: Application

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
        }
    }

}