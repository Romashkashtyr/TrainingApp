package com.example.trainingapp.presentation.base.main_logic

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.data.MainRepositoryImpl
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.main_logic.rc_view.DashboardAdapter
import com.example.trainingapp.presentation.base.start.MainPresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import moxy.ktx.moxyPresenter

class MainActivity : BaseActivity(), MainView, DashboardAdapter.OnClick {
    private val mainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardAdapter: DashboardAdapter
    private val repository = MainRepositoryImpl()

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    private var stepsCount = 0
    private var waterIntake = 0
    private var workoutCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

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
        scope.launch {
            val waterAmount = repository.getWaterInfo()
            list.add(waterAmount)
        }


        binding.dashboardRecyclerView.apply {
            layoutManager =LinearLayoutManager(this@MainActivity)
            adapter = dashboardAdapter
        }
    }

    override fun addWater(waterCount: Int) {
        TODO("Not yet implemented")
    }
}