package com.example.main.di.modules



import com.example.database.TrainingRoomDatabase
import com.example.main.data.repository.MainRepositoryImpl
import com.example.main.data.repository.StepsRepositoryImpl
import com.example.main.data.repository.WaterRepositoryImpl
import com.example.main.data.repository.WorkoutRepositoryImpl
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import com.example.main.domain.repository.WaterRepository
import com.example.main.domain.repository.WorkoutRepository
import com.example.main.ui.activity.MainPresenter
import com.example.main.ui.steps_fragment.StepsFragmentPresenter
import com.example.main.ui.water_fragment.WaterFragmentPresenter
import com.example.main.ui.workout_history_fragment.WorkoutHistoryFragmentPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Module
interface MainModule {

    @Binds
    @Singleton
    fun bindsMainRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    @Singleton
    fun bindStepsRepository(impl: StepsRepositoryImpl): StepsRepository

    @Binds
    @Singleton
    fun bindWaterRepository(impl: WaterRepositoryImpl): WaterRepository

    @Binds
    @Singleton
    fun bindWorkoutRepository(impl: WorkoutRepositoryImpl): WorkoutRepository

    companion object {
        @Provides
        @Singleton
        fun provideTrainingRoomDatabase(): TrainingRoomDatabase {
            return TrainingRoomDatabase.getInstanceDb()
        }
    }

}

@Singleton
class WaterFragmentFactory @Inject constructor(
    private val presenter: Provider<WaterFragmentPresenter>
) {
    fun createWaterFragmentPresenter(): WaterFragmentPresenter {
        return presenter.get()
    }
}


@Singleton
class StepsFragmentFactory @Inject constructor(
    private val presenter: Provider<StepsFragmentPresenter>
) {
    fun createPresenter(): StepsFragmentPresenter {
        return presenter.get()
    }
}

@Singleton
class WorkoutFragmentFactory @Inject constructor (
    private val presenter: Provider<WorkoutHistoryFragmentPresenter>
) {
    fun createPresenter(): WorkoutHistoryFragmentPresenter {
        return presenter.get()
    }
}

@Singleton
class MainPresenterFactory @Inject constructor(
    private val presenter: Provider<MainPresenter>
) {
    fun createMainPresenter(): MainPresenter {
        return presenter.get()
    }
}