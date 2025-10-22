package com.example.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseActivity
import com.example.core.structures.DashboardItem
import com.example.main.databinding.ActivityMainBinding
import com.example.main.ui.adapters.DashboardAdapterDelegates
import com.example.trainings.domain.events.OnAddWaterClicked
import com.example.trainings.domain.events.OnTrainingClick
import com.example.trainings.domain.events.OnViewTrainingsClicked
import com.example.trainings.ui.TrainingsListActivity
import moxy.ktx.moxyPresenter

class MainActivity : BaseActivity(), MainView, OnAddWaterClicked,
    OnTrainingClick, OnViewTrainingsClicked {
    private val mainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding



    private val items = listOf(
        DashboardItem.StepsItem(5000),
        DashboardItem.WaterItem(1500),
        DashboardItem.WorkoutItem(3),
        DashboardItem.TrainingListItem()
    )

    private val adapterDelegate = DashboardAdapterDelegates(
        onAddWaterClicked = this,
        onTrainingClick = this,
        items = items
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
    }


    override fun onAddWaterClicked(newAmount: Int) {
        mainPresenter.requestAddWater(newAmount)
    }


    override fun onViewTrainingsClicked() {
        TODO()
    }

    override fun onTrainingClick() {
        val intent = Intent(this, TrainingsListActivity::class.java)
        startActivity(intent)
    }

    override fun initListData(waterAmount: Int) {
        binding.dashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterDelegate
        }
    }

    override fun addWater(waterCount: Int) {
        mainPresenter.requestAddWater(waterCount)
    }

    companion object {
        fun getInstance(fromContext: Context) = Intent(fromContext, MainActivity::class.java)
    }
}
