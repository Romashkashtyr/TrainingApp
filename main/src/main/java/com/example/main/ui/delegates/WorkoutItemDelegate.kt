package com.example.main.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.main.OnWorkoutClick
import com.example.main.structures.DashboardItem
import com.example.main.databinding.ItemWorkoutBinding
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

class WorkoutItemDelegate(
    private val onWorkoutClick: OnWorkoutClick
) : AdapterDelegate<List<DashboardItem>>() {
    override fun isForViewType(items: List<DashboardItem>, position: Int): Boolean {
        return items[position] is DashboardItem.WorkoutItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return WorkoutViewHolder(
            ItemWorkoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onWorkoutItemClick = onWorkoutClick::onWorkoutClick
        )
    }

    override fun onBindViewHolder(
        items: List<DashboardItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        (holder as WorkoutViewHolder).bind(items[position] as DashboardItem.WorkoutItem)
    }

    inner class WorkoutViewHolder(
        private val binding: ItemWorkoutBinding,
        private val onWorkoutItemClick: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DashboardItem.WorkoutItem) {
            binding.workoutsCount.text = item.workoutCounts.toString()
            binding.root.setOnClickListener {
                onWorkoutItemClick()
            }
        }
    }
}