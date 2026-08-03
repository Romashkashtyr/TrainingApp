package com.example.main.ui.activity

import com.example.core.base.BasePresenter
import com.example.core.data.datastore.StepsDataStore
import com.example.core.structures.Status
import com.example.main.R
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import com.example.main.ui.activity.MainView
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


    fun observeSteps(date: String) {
        stepsJob?.cancel()

        stepsJob = launch {
            stepsRepository.observeTodaySteps(date)
                .collect { steps ->
                    onMainThread {
                        viewState.updateSteps(steps ?: 0)
                    }
                }
        }
    }

}