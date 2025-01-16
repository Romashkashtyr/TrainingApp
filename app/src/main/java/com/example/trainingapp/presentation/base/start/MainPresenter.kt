package com.example.trainingapp.presentation.base.start


import com.example.trainingapp.R
import com.example.trainingapp.data.MainRepositoryImpl
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Status
import com.example.trainingapp.presentation.base.BasePresenter
import com.example.trainingapp.presentation.base.main_logic.MainView
import com.example.trainingapp.presentation.base.main_logic.rc_view.DashboardAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.InjectViewState

@InjectViewState
class MainPresenter: BasePresenter<MainView>() {
    private val repository = MainRepositoryImpl()


    fun requestGetScreenData() {
        launch {
            val waterAmount = repository.getWaterAmount()
            withContext(Dispatchers.IO) {
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
        launch { repository.addWater(amount) }
    }



}