package com.example.main.ui.workout_history_fragment

import com.example.core.base.BaseFragmentPresenter
import com.example.main.domain.usecase.GetWorkoutHistoryUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject


@InjectViewState
class WorkoutHistoryFragmentPresenter @Inject constructor(
    private val getWorkoutHistoryUseCase: GetWorkoutHistoryUseCase
): BaseFragmentPresenter<WorkoutHistoryView>() {

    private var historyJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        observeWorkoutHistory()
    }

    private fun observeWorkoutHistory() {
        historyJob?.cancel()
        historyJob = launch {
            try {
                viewState.showLoading()
                getWorkoutHistoryUseCase()
                    .collect { history ->
                        onMainThread {
                            viewState.hideLoading()
                            if (history.isEmpty()) {
                                viewState.showEmptyHistory()
                            } else {
                                viewState.hideEmptyHistory()
                            }
                            viewState.showWorkoutHistory(history)
                        }
                    }
            } catch (e: Exception) {
                onMainThread {
                    viewState.hideLoading()
                    viewState.showEmptyHistory()
                    viewState.showWorkoutHistory(emptyList())
                }
            }
        }
    }

    override fun onDestroy() {
        historyJob?.cancel()
        super.onDestroy()
    }
}