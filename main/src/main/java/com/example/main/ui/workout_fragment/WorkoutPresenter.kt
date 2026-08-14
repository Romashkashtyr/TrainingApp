package com.example.main.ui.workout_fragment

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
class WorkoutPresenter @Inject constructor(
    private val getWorkoutUseCase: GetWorkoutUseCase
): BaseFragmentPresenter<WorkoutView>() {



//    private var workout: List<WorkoutExercise> = emptyList()
//
//    private var currentExerciseIndex = 0
//
//    private var currentTimer: CountDownTimer? = null
//
//    private var currentLevel: WorkoutLevel = WorkoutLevel.EASY
//
//    fun loadWorkout(
//        type: WorkoutType,
//        level: WorkoutLevel
//    ) {
//        currentLevel = level
//
//        launch {
//            try {
//                workout = getWorkoutUseCase(
//                    type = type,
//                    level = level
//                )
//
//                if (workout.isEmpty()) {
//                    viewState.showError("Не удалось подобрать упражнения")
//                    return@launch
//                }
//
//                currentExerciseIndex = 0
//
//                onMainThread {
//                    viewState.showWorkout(workout)
//                    showCurrentExercise()
//                }
//
//            } catch (e: Exception) {
//                onMainThread {
//                    viewState.showError(
//                        e.message ?: "Ошибка загрузки тренировки"
//                    )
//                }
//            }
//        }
//    }
//
//    private fun showCurrentExercise() {
//        if (currentExerciseIndex >= workout.size) {
//            viewState.showWorkoutFinished()
//            return
//        }
//
//        val exercise = workout[currentExerciseIndex]
//
//        viewState.showExercise(
//            exercise = exercise,
//            position = currentExerciseIndex,
//            total = workout.size
//        )
//
//        startTimer(exercise.durationSeconds)
//    }
//
//    private fun startTimer(seconds: Int) {
//        currentTimer?.cancel()
//        viewState.updateTimer(seconds)
//
//        currentTimer = object : CountDownTimer(
//            seconds * 1000L,
//            1000L
//        ) {
//            override fun onTick(millisUntilFinished: Long) {
//                val secondsLeft = (millisUntilFinished / 1000L).toInt()
//                viewState.updateTimer(secondsLeft)
//            }
//
//            override fun onFinish() {
//                currentExerciseIndex++
//                showCurrentExercise()
//            }
//
//        }.start()
//    }
//
//    fun nextExercise() {
//        currentTimer?.cancel()
//
//        currentExerciseIndex++
//
//        showCurrentExercise()
//    }
//
//    fun stopWorkout() {
//        currentTimer?.cancel()
//        currentTimer = null
//    }
//
//    override fun onDestroy() {
//        currentTimer?.cancel()
//        currentTimer = null
//        super.onDestroy()
//    }
}