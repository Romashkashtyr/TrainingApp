package com.example.trainingapp.presentation.base.start


import com.example.trainingapp.data.AuthRepositoryImpl
import com.example.trainingapp.data.MainRepositoryImpl
import com.example.trainingapp.data.WaterIntake
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.presentation.base.MainPresenterInterface
import com.example.trainingapp.presentation.base.main_logic.rc_view.DashboardAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import moxy.MvpPresenter

class MainPresenter: MvpPresenter<MainPresenterInterface>() {
    private val repository = MainRepositoryImpl()

    private lateinit var adapter: DashboardAdapter


    private fun initAdapter(){
        adapter = DashboardAdapter(::onAddWaterClicked, arrayListOf<DashboardItem>())

    }


    suspend fun onAddWaterClicked(amount: Int) {
        val waterAmount = DashboardItem.WaterItem(amount)
        repository.getWaterInfo()


    }

    private fun getCurrentWaterLevel(water: DashboardItem.WaterItem) {
        adapter.updateWaterLevel(water)
    }
}