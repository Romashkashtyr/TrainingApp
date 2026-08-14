package com.example.main.ui.workout_history_fragment

import android.os.CountDownTimer
import com.example.core.base.BaseFragmentPresenter
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.domain.usecase.GetWorkoutUseCase
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class WorkoutHistoryFragmentPresenter @Inject constructor(
    private val getWorkoutUseCase: GetWorkoutUseCase
): BaseFragmentPresenter<WorkoutHistoryView>() {


}