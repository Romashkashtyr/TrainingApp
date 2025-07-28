package com.example.trainingapp.presentation.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        onTrainingClick()
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
        val list = mutableListOf(
            DashboardItem.StepsItem(100), DashboardItem.WaterItem(waterAmount)
        )
        dashboardAdapter = DashboardAdapter(this, list)
        binding.dashboardRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = dashboardAdapter
        }
    }

    override fun addWater(waterCount: Int) {
        mainPresenter.requestAddWater(waterCount)
    }

    companion object {
        fun getInstance(fromContext: Context) = Intent(fromContext, MainActivity::class.java)
    }
}
