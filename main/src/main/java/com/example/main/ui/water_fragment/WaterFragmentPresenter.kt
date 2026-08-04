package com.example.main.ui.water_fragment

import com.example.core.base.BaseFragmentPresenter
import com.example.main.domain.usecase.GetTodayWaterUseCase
import com.example.main.domain.usecase.GetWaterHistoryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class WaterFragmentPresenter @Inject constructor(
    private val getTodayWaterUseCase: GetTodayWaterUseCase,
    private val getWaterHistoryUseCase: GetWaterHistoryUseCase,
) : BaseFragmentPresenter<WaterView>() {

    private var historyJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadTodayWater()
        observeHistory()
    }


    private fun loadTodayWater() {
        launch {
            try {
                val amount = getTodayWaterUseCase()

                onMainThread {
                    viewState.showTodayWater(amount)
                }
            } catch (e: Exception) {
                onMainThread {
                    viewState.showTodayWater(0)
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
                }
            }
        }
    }

    override fun onDestroy() {
        historyJob?.cancel()
        super.onDestroy()

    }
}


