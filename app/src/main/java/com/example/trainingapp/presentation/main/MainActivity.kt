package com.example.trainingapp.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.rc_view.DashboardAdapter
import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import moxy.ktx.moxyPresenter
import java.util.concurrent.Executors

class MainActivity : BaseActivity(), MainView, DashboardAdapter.OnClick {
    private val mainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var bindingItem: ItemTrainingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingItem = ItemTrainingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        toTrainingList()
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
            mainPresenter.requestAddWater(waterCount)
    }

    override fun toTrainingList() {
        bindingItem.viewWorkoutsButton.setOnClickListener {
            val intent = Intent(this, TrainingsListActivity::class.java)
            startActivity(intent)
        }
    }
}