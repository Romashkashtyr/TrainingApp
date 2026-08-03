package com.example.main.ui.water_rc_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.R
import com.example.main.data.entitieModules.Water
import com.example.main.databinding.ItemWaterHistoryBinding

class WaterAdapter(): ListAdapter<Water, WaterAdapter.WaterViewHolder>(WaterDiffCallback()) {


    inner class WaterViewHolder(
        private val binding: ItemWaterHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Water) {
            binding.dateText.text = item.date
            binding.waterAmount.text = "${item.amount} мл"

            binding.dayStatus.text = if (item.amount >= 2500) {
                "Дневная норма выполнена"
            } else {
                "До нормы: ${2500 - item.amount} мл"
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WaterViewHolder {
        val binding = ItemWaterHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WaterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}