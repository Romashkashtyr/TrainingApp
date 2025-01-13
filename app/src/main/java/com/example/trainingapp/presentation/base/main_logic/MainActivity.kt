package com.example.trainingapp.presentation.base.main_logic

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.data.MainRepositoryImpl
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.Status
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.BasePresenter
import com.example.trainingapp.presentation.base.main_logic.rc_view.DashboardAdapter
import com.example.trainingapp.presentation.base.start.MainPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

class MainActivity : BaseActivity(), MainView, DashboardAdapter.OnClick {
    private val mainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardAdapter: DashboardAdapter


    private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
    private val scope = CoroutineScope(dispatcher)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        observeScreenData()
    }


    private fun observeScreenData() {
        mainPresenter.screenData
            .onEach { status ->
                when(status) {
                    is Status.Success -> initListData(status.info)
                    else -> Unit
                }
            }.launchIn(scope)
    }

    override fun onAddWaterClicked(newAmount: Int) {
        mainPresenter.requestAddWater(newAmount)
    }

    override fun onViewTrainingsClicked() {
        TODO()
    }

    override fun initListData(waterAmount: Int) {
        val list = mutableListOf<DashboardItem>(
            DashboardItem.StepsItem(100),
            DashboardItem.WaterItem(waterAmount)
        )
        dashboardAdapter = DashboardAdapter(this, list)
        binding.dashboardRecyclerView.apply {
            layoutManager =LinearLayoutManager(this@MainActivity)
            adapter = dashboardAdapter
        }
    }

    override fun addWater(waterCount: Int){
        scope.launch {
            mainPresenter.requestAddWater(waterCount)
        }

    }
}