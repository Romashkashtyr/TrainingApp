package com.example.main.ui


import com.example.core.base.BasePresenter
import com.example.core.structures.Status
import com.example.main.R
import com.example.main.domain.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
class MainPresenter(
    private val mainRepository: MainRepository
): BasePresenter<MainView>() {


    fun requestGetScreenData() {
        launch {
            val waterAmount = mainRepository.getWaterAmount()
            withContext(Dispatchers.Main.immediate) {
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



}