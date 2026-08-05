package com.example.main.ui.activity

import com.example.core.base.BasePresenter
import com.example.core.data.datastore.StepsDataStore
import com.example.core.structures.Status
import com.example.main.R
import com.example.main.domain.repository.MainRepository
import com.example.main.domain.repository.StepsRepository
import com.example.main.domain.repository.WaterRepository
import com.example.main.domain.usecase.AddWaterUseCase
import com.example.main.domain.usecase.GetTodayWaterUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val mainRepository: MainRepository,
    private val waterRepository: WaterRepository,
    private val stepsRepository: StepsRepository,
    private val stepsDataStore: StepsDataStore,
    private val addWaterUseCase: AddWaterUseCase,
    private val getTodayWaterUseCase: GetTodayWaterUseCase,
) : BasePresenter<MainView>() {


    private var stepsJob: Job? = null

    private var waterJob: Job? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadTodayWater()
    }

    private fun loadTodayWater() {
        launch {
            try {
                val amount = getTodayWaterUseCase()

                onMainThread {
                    viewState.updateWater(amount)
                }
            } catch (e: Exception) {
                onMainThread {
                    viewState.updateWater(0)
                }
            }

        }
    }

    fun requestAddWater(amount: Int) {
        launch {
            try {
                addWaterUseCase(amount)

                loadTodayWater()
            } catch (e: Exception) {
                onMainThread {
                    viewState.showToast(e.message ?: "Ошибка при добавлении воды")
                }
            }
        }
    }

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

//    fun requestAddWater(amount: Int) {
//        launch { mainRepository.addWater(amount) }
//    }

    fun observeWater() {
        waterJob?.cancel()

        waterJob = launch {
            waterRepository.observeTodayWater()
                .collect { amount ->
                    onMainThread {
                        viewState.updateWater(amount)
                    }
                }
        }
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