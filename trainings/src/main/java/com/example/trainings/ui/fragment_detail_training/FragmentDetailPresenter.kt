package com.example.trainings.ui.fragment_detail_training

import android.util.Log
import com.example.core.base.BaseFragmentPresenter
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.GetExerciseByIdUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FragmentDetailPresenter @Inject constructor(
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : BaseFragmentPresenter<ExerciseDetailView>() {


    private var currentExercise: FullExercise? = null

     fun loadExercise(id: String) {

         Log.d("DEBUG_APP", "Presenter loadExercise START id=$id")

        launch {
            try {

                viewState.showLoading()

                Log.d("DEBUG_APP", "before usecase call")
                val exercise = getExerciseByIdUseCase(id)

                Log.d("DEBUG_APP", "usecase success")
                val isFavorite = toggleFavoriteUseCase.isFavorite(exercise.id)

                currentExercise = exercise


                withContext(Dispatchers.Main) {
                    viewState.showExercise(exercise)
                    Log.d("DEBUG_APP", "viewState.showExercise called")
                    viewState.updateFavoriteState(isFavorite)
                }

            } catch (e: Exception) {

                Log.e("DEBUG_APP", "PRESENTER CRASH", e)
                viewState.showToastInfo(e.message ?: "Error")

            } finally {

                viewState.stopLoading()

                Log.d("DEBUG_APP", "Presenter loadExercise END")
            }
        }
    }

    fun onFavoriteClicked() {

        currentExercise?.let { exercise ->
            launch {
                val newState = toggleFavoriteUseCase(exercise)
                currentExercise = exercise.copy(
                    isFavorite = newState
                )

                viewState.updateFavoriteState(newState)
            }
        }
    }

}