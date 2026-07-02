package com.example.trainings.ui.fragment_favorites

import com.example.core.base.BaseView
import com.example.trainings.data.response.FullExercise
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FavoritesView: BaseView {

    fun showExercises(exercises: List<FullExercise>)

    fun showLoading()

    fun stopLoading()

}