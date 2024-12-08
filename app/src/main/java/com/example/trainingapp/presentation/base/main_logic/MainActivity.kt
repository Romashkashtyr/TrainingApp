package com.example.trainingapp.presentation.base.main_logic

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trainingapp.databinding.ActivityMainBinding
import com.example.trainingapp.presentation.base.BaseActivity
import com.example.trainingapp.presentation.base.main_logic.rc_view.DashboardAdapter

class MainActivity : BaseActivity(), DashboardAdapter.OnClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dashboardAdapter: DashboardAdapter

    private var stepsCount = 0
    private var waterIntake = 0
    private var workoutCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        dashboardAdapter = DashboardAdapter(this, emptyList())

        binding.dashboardRecyclerView.apply {
            layoutManager =LinearLayoutManager(this@MainActivity)
            adapter = dashboardAdapter
        }
    }

    override fun onAddWaterClicked() {
        waterIntake += 200

    }

    override fun onViewTrainingsClicked() {
        TODO()
    }
}