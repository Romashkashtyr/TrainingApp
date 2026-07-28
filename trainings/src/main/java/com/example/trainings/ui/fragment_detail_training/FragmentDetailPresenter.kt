package com.example.trainings.ui.fragment_detail_training

import android.util.Log
import com.example.core.base.BaseFragmentPresenter
import com.example.trainings.data.response.FullExercise
import com.example.trainings.domain.usecases.GetExerciseByIdUseCase
import com.example.trainings.domain.usecases.ToggleFavoriteUseCase
import com.example.trainings.domain.usecases.UpdateFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class FragmentDetailPresenter @Inject constructor(
    private val getExerciseByIdUseCase: GetExerciseByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : BaseFragmentPresenter<ExerciseDetailView>() {

    private var currentExercise: FullExercise? = null

    private var startFavorite: Boolean? = null

    private var isFavorite: Boolean? = null

    private lateinit var exerciseId: String


     fun loadExercise(id: String) {

         Log.d("DEBUG_APP", "Presenter loadExercise START id=$id")

        launch {
            try {

                viewState.showLoading()

                Log.d("DEBUG_APP", "before usecase call")
                val exercise = getExerciseByIdUseCase(id)

                Log.d("DEBUG_APP", "usecase success")
                isFavorite = toggleFavoriteUseCase.isFavorite(exercise.id)
                startFavorite = isFavorite
                exerciseId = exercise.id

                currentExercise = exercise

                withContext(Dispatchers.Main) {
                    viewState.showExercise(exercise)
                    Log.d("DEBUG_APP", "viewState.showExercise called")
                    isFavorite?.let {
                        viewState.updateFavoriteState(it)
                    }
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

    fun getExerciseId(): String = exerciseId
    fun getFavorite(): Boolean = isFavorite ?: false

    fun isFavoriteChanged(): Boolean = if (startFavorite == null) false else (startFavorite == isFavorite)

    fun onFavoriteClicked() {
        isFavorite = isFavorite?.let { !it } ?: return
        currentExercise?.let { exercise ->
            launch {
                isFavorite?.let {newState ->
                    updateFavoriteUseCase(exercise.id, newState)

                    viewState.updateFavoriteState(newState)
                }
            }
        }
    }

}