package com.example.main.di

import com.example.core.di.CoreComponent
import com.example.core.providers.ExercisesProvider
import com.example.main.di.modules.MainModule
import com.example.main.service.StepsCounterService
import com.example.main.ui.activity.MainActivity
import com.example.main.ui.steps_fragment.StepsFragment
import com.example.main.ui.water_fragment.WaterFragment
import com.example.main.ui.workout_fragment.WorkoutFragment
import com.example.main.ui.workout_history_fragment.WorkoutHistoryHistoryFragment
import com.example.main.ui.workout_running.WorkoutRunningFragment
import dagger.Component
import java.lang.IllegalStateException
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class], dependencies = [CoreComponent::class, ExercisesProvider::class])
interface MainComponent {

    @Component.Builder
    interface Builder {
        fun coreComponent(coreComponent: CoreComponent): Builder

        fun exercisesProvider(
            exercisesProvider: ExercisesProvider
        ): Builder

        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(service: StepsCounterService)

    fun inject(fragment: WaterFragment)

    fun inject(fragment: StepsFragment)

    fun inject(fragment: WorkoutHistoryHistoryFragment)

    fun inject(fragment: WorkoutFragment)

    fun inject(fragment: WorkoutRunningFragment)

    companion object {
        private var instance: MainComponent? = null

        fun init(
            coreComponent: CoreComponent,
            exercisesProvider: ExercisesProvider
        ): MainComponent {
            if (instance == null) {
                instance = DaggerMainComponent.builder()
                    .coreComponent(coreComponent)
                    .exercisesProvider(exercisesProvider)
                    .build()
            }

            return instance!!
        }

        fun getMainInstance(): MainComponent {
            return if (instance != null) instance!! else throw IllegalStateException()
        }
    }
}