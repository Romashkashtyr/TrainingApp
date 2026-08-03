package com.example.main.ui.water_fragment

import com.example.core.base.BaseFragment
import com.example.main.data.entitieModules.Water
import com.example.main.databinding.WaterFragmentBinding

class WaterFragment: BaseFragment(), WaterView {

    private val _binding: WaterFragmentBinding? = null

    val binding get() = _binding!!
    override fun showTodayWater(amount: Int) {
        TODO("Not yet implemented")
    }

    override fun showWaterHistory(history: List<Water>) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun stopLoading() {
        TODO("Not yet implemented")
    }
}