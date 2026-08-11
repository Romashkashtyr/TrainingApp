package com.example.main.ui.workout_rc_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.main.data.entitieModules.WorkoutHistory
import com.example.main.data.entitieModules.WorkoutLevel
import com.example.main.databinding.ItemWorkoutHistoryBinding

class WorkoutHistoryAdapter :
    ListAdapter<WorkoutHistory, WorkoutHistoryAdapter.WorkoutHistoryViewHolder>(
        WorkoutDiffCallback()
    ) {

    inner class WorkoutHistoryViewHolder(
        private val binding: ItemWorkoutHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WorkoutHistory) {
            binding.workoutName.text = item.workoutName

            binding.workoutLevel.text = when (item.level) {
                WorkoutLevel.EASY -> "Легкий"
                WorkoutLevel.MEDIUM -> "Средний"
                WorkoutLevel.HARD -> "Сложный"
            }

            binding.workoutDate.text = item.date
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WorkoutHistoryViewHolder {
        val binding =
            ItemWorkoutHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return WorkoutHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: WorkoutHistoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }


}