package com.example.main.ui.steps_rc_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.data.entitieModules.Steps
import com.example.main.databinding.ItemStepsHistoryBinding

class StepsAdapter: ListAdapter<Steps, StepsAdapter.StepsViewHolder>(StepsDiffCallback()) {


    inner class StepsViewHolder(
        private val binding: ItemStepsHistoryBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Steps) {
            binding.dateText.text = item.date
            binding.stepsValue.text = "${item.steps} шагов"

            binding.dayStatus.text = if (item.steps >= 10_000) {
                "Дневная норма выполнена"
            } else {
                "До нормы: ${10_000 - item.steps} шагов"
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StepsViewHolder {
        val binding = ItemStepsHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StepsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}