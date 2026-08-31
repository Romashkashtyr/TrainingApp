package com.example.main.ui.workout_running

import android.os.CountDownTimer
import com.example.core.base.BaseFragmentPresenter
import com.example.main.data.entitieModules.WorkoutExercise
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.data.entitieModules.WorkoutType
import com.example.main.domain.usecase.GetWorkoutHistoryUseCase
import com.example.main.domain.usecase.GetWorkoutUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WorkoutRunningPresenter @Inject constructor(
    private val getWorkoutUseCase: GetWorkoutUseCase
): BaseFragmentPresenter<WorkoutRunningView>()  {

    private var workout: List<WorkoutExercise> = emptyList()

    private var currentExerciseIndex = 0

    private var countDownTimer: CountDownTimer? = null

    fun loadWorkout(
        type: WorkoutType,
        level: WorkoutLevel
    ) {

        launch {

            try {

                val result = getWorkoutUseCase(
                    type = type,
                    level = level
                )

                workout = result
                currentExerciseIndex = 0

                onMainThread {
                    if (workout.isEmpty()) {
                        viewState.showError(
                            "В тренировке нет упражнений"
                        )
                        return@onMainThread
                    }
                    showCurrentExercise()
                }
            } catch (e: Exception) {
                onMainThread {
                    viewState.showError(
                        e.message
                            ?: "Не удалось загрузить тренировку"
                    )
                }
            }
        }
    }

    private fun showCurrentExercise() {
        if (currentExerciseIndex >= workout.size) {
            stopTimer()
            viewState.showWorkoutFinished()
            return
        }

        val exercise =
            workout[currentExerciseIndex]

        viewState.showExercise(
            exercise = exercise,
            position = currentExerciseIndex,
            total = workout.size
        )

        startTimer(
            exercise.durationSeconds
        )
    }

    private fun startTimer(
        durationSeconds: Int
    ) {

        stopTimer()

        viewState.updateTimer(
            durationSeconds
        )

        countDownTimer = object : CountDownTimer(
            durationSeconds * 1000L,
            1000L
        ) {

            override fun onTick(
                millisUntilFinished: Long
            ) {

                val seconds =
                    (millisUntilFinished / 1000L)
                        .toInt()

                viewState.updateTimer(
                    seconds
                )
            }

            override fun onFinish() {

                currentExerciseIndex++

                showCurrentExercise()
            }

        }.start()
    }

    fun nextExercise() {
        stopTimer()

        currentExerciseIndex++

        showCurrentExercise()
    }

    fun stopWorkout() {
        stopTimer()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
    }

    override fun onDestroy() {
        stopTimer()

        super.onDestroy()
    }
}