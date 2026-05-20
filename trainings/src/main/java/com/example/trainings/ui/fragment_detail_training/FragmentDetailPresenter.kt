package com.example.trainings.ui.fragment_detail_training

import com.example.core.base.BaseFragmentPresenter
import com.example.trainings.R
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.GetCombineDataAndImageTrainings
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FragmentDetailPresenter @Inject constructor(
    private val getCombineDataAndImageTrainings: GetCombineDataAndImageTrainings,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : BaseFragmentPresenter<ExerciseDetailView>() {


    private var currentExercise: FullExercise? = null

     fun loadExercise(id: String) {

        launch {
            try {

                viewState.showLoading()

                val exercises = getCombineDataAndImageTrainings()

                val exercise = exercises.firstOrNull { it.id == id }

                if (exercise == null) {
                    viewState.showToastInfo(R.string.training_not_found)
                    return@launch
                }

                currentExercise = exercise


                viewState.showExercise(exercise)

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
                val favoriteState = toggleFavoriteUseCase(exercise)
                viewState.updateFavoriteState(favoriteState)
            }
        }
    }

}