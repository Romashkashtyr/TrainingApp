package com.example.main.ui


import android.util.Log
import com.example.core.base.BasePresenter
import com.example.core.data.datastore.StepsDataStore
import com.example.core.exception.ExceptionCatcher
import com.example.core.structures.Status
import com.example.core.utils.getTodayDate
import com.example.main.R
import com.example.main.domain.MainRepository
import com.example.main.domain.StepsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository,
    private val stepsRepository: StepsRepository,
    private val stepsDataStore: StepsDataStore,
) : BasePresenter<MainView>() {


    private var stepsJob: Job? = null

    fun requestGetScreenData() {
        launch {
            val waterAmount = mainRepository.getWaterAmount()
            onMainThread {
                when (waterAmount) {
                    is Status.Failure -> {
                        viewState.showToast(R.string.sign_up_failure)
                    }

                    is Status.NoNetwork -> {
                        viewState.showToast(R.string.network_failure)
                    }

                    is Status.Success -> {
                        viewState.initListData(waterAmount.info)
                    }
                }
            }
        }
    }

    fun requestAddWater(amount: Int) {
        launch { mainRepository.addWater(amount) }
    }


    fun observeSteps() {
        stepsJob?.cancel()

        stepsJob = launch {
            stepsRepository.observeSteps()
                .collect { steps ->
                    onMainThread {
                        viewState.updateSteps(steps)
                    }
                }
        }
    }

    fun onStepReceived(totalSteps: Float) {
        launch {
                val today = getTodayDate()
                val savedDate = stepsDataStore.getDate()
                var initialSteps = stepsDataStore.getInitialSteps()

                if (savedDate != today || initialSteps == null) {
                    initialSteps = totalSteps
                    stepsDataStore.saveInitialSteps(totalSteps)
                    stepsDataStore.saveDate(today)
                }

                val currentSteps = (totalSteps - initialSteps).toInt().coerceAtLeast(0)

                stepsDataStore.saveCurrentSteps(currentSteps)

                onMainThread {
                    viewState.updateSteps(currentSteps)
                }
            }

        }
    }


