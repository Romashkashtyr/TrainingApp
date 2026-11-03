package com.example.main.ui.water_fragment

import com.example.core.base.BaseFragmentPresenter
import com.example.main.domain.usecase.AddWaterUseCase
import com.example.main.domain.usecase.GetWaterHistoryUseCase
import com.example.main.domain.usecase.ObserveTodayWaterUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class WaterFragmentPresenter @Inject constructor(
    private val observeTodayWaterUseCase: ObserveTodayWaterUseCase,
    private val getWaterHistoryUseCase: GetWaterHistoryUseCase,
    private val addWaterUseCase: AddWaterUseCase
) : BaseFragmentPresenter<WaterView>() {

    private var historyJob: Job? = null
    private var todayWaterJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeTodayWater()
        observeHistory()
    }

    private fun observeTodayWater() {
        todayWaterJob?.cancel()
        todayWaterJob = launch {
            observeTodayWaterUseCase().collect { amount ->
                onMainThread {
                    viewState.showTodayWater(amount)
                }
            }
        }
    }

    private fun observeHistory() {
        historyJob?.cancel()
        historyJob = launch {
            getWaterHistoryUseCase().collect { history ->
                onMainThread {
                    viewState.showWaterHistory(history)
                    viewState.showEmptyHistory(history.isEmpty())
                }
            }
        }
    }

    fun addWater(amount: Int) {
        launch {
            try {
                addWaterUseCase(amount)
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    override fun onDestroy() {
        historyJob?.cancel()
        todayWaterJob?.cancel()
        super.onDestroy()
    }
}


