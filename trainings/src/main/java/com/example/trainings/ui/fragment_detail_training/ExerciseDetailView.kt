package com.example.trainings.ui.fragment_detail_training

import com.example.core.base.BaseFragmentView
import com.example.core.base.BaseView
import com.example.trainings.data.response.FullExercise
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ExerciseDetailView : BaseFragmentView {

    fun showExercise(exercise: FullExercise)

    fun showLoading()

    fun stopLoading()

    fun updateFavoriteState(isFavorite: Boolean)
}