package com.example.trainingapp.presentation.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.Constants.AUTH_PREFS
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.domain.OnClick
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.main.adapters.DashboardAdapterDelegates
import com.example.trainingapp.presentation.main.rc_view.DashboardAdapter
import com.example.trainingapp.presentation.trainings.TrainingsListActivity
import moxy.ktx.moxyPresenter

class MainActivity : BaseActivity(), MainView, DashboardAdapter.OnClick {
    private val mainPresenter by moxyPresenter { MainPresenter() }
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardAdapter: DashboardAdapter

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences(AUTH_PREFS, Context.MODE_PRIVATE)
    }

    val items = listOf(
        DashboardItem.StepsItem(5000),
        DashboardItem.WaterItem(1500),
        DashboardItem.WorkoutItem(3),
        DashboardItem.TrainingListItem()
    )

    val adapter = DashboardAdapterDelegates(
        onClick = object : OnClick {
            override fun onAddWaterClicked(newAmount: Int) {
                Toast.makeText(this@MainActivity, "Added $newAmount ml of water", Toast.LENGTH_LONG).show()
            }

            override fun onViewTrainingsClicked() {
                TODO("Not yet implemented")
            }

            override fun onTrainingClick() {
                TODO("Not yet implemented")
            }

        },
        items = items
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainPresenter.requestGetScreenData()
        onTrainingClick()
        adapter.updateItems(items)
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
