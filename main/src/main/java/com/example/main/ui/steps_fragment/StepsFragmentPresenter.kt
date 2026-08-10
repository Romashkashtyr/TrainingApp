package com.example.main.ui.steps_fragment

import com.example.core.base.BaseFragmentPresenter
import com.example.main.domain.repository.StepsRepository
import com.example.main.domain.repository.WaterRepository
import com.example.main.domain.usecase.GetStepsHistoryUseCase
import com.example.main.domain.usecase.GetTodayStepsUseCase
import com.example.main.domain.usecase.GetTodayWaterUseCase
import com.example.main.domain.usecase.GetWaterHistoryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class StepsFragmentPresenter @Inject constructor(
    private val getTodayStepsUseCase: GetTodayStepsUseCase,
    private val getStepsHistoryUseCase: GetStepsHistoryUseCase,
) : BaseFragmentPresenter<StepsView>() {

    private var historyJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadTodaySteps()
        observeHistory()
    }

    private fun loadTodaySteps() {
        launch {
            try {
                val stepsCount = getTodayStepsUseCase()

                onMainThread {
                    viewState.showTodaySteps(stepsCount)
                }
            } catch (e: Exception) {
                onMainThread {
                    viewState.showTodaySteps(0)
                }
            }

        }
    }

    private fun observeHistory() {
        historyJob?.cancel()
        historyJob = launch {
            getStepsHistoryUseCase().collect { history ->
                onMainThread {
                    viewState.showStepsHistory(history)
                }
            }
        }
    }

    override fun onDestroy() {
        historyJob?.cancel()
        super.onDestroy()

    }
}


