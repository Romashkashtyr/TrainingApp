package com.example.trainingapp.presentation.main.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem
import com.example.trainingapp.presentation.main.rc_view.DashboardViewHolder
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class WorkoutItemDelegate() :AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.WorkoutItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return DashboardViewHolder.WorkoutsViewHolder(
            ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as DashboardViewHolder.WorkoutsViewHolder).bind(items[position] as DashboardItem.WorkoutItem)
    }

    inner class WorkoutViewHolder(
        private val binding: ItemWorkoutBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.WorkoutItem) {
            binding.workoutsCount.text = item.workoutCounts.toString()
        }
    }
}