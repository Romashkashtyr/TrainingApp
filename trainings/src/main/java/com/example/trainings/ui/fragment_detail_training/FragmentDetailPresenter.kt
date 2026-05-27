package com.example.trainings.ui.fragment_detail_training

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

        launch {
            try {

                viewState.showLoading()

                val exercise = getExerciseByIdUseCase(id)
                val isFavorite = toggleFavoriteUseCase.isFavorite(exercise.id)

                currentExercise = exercise
                withContext(Dispatchers.Main) {
                    viewState.showExercise(exercise)
                    viewState.updateFavoriteState(isFavorite)
                }

            } catch (e: Exception) {

                viewState.showToastInfo(e.message ?: "Error")

            } finally {

                viewState.stopLoading()
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