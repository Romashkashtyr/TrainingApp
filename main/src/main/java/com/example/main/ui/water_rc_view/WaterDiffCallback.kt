package com.example.main.ui.water_rc_view

import androidx.recyclerview.widget.DiffUtil
import com.example.main.data.entitieModules.Water

class WaterDiffCallback: DiffUtil.ItemCallback<Water>() {
    override fun areItemsTheSame(
        oldItem: Water,
        newItem: Water
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: Water,
        newItem: Water
    ): Boolean {
        return oldItem == newItem
    }
}