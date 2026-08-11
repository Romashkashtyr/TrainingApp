package com.example.main.ui.workout_rc_view

import androidx.recyclerview.widget.DiffUtil
import com.example.main.data.entitieModules.WorkoutHistory

class WorkoutDiffCallback: DiffUtil.ItemCallback<WorkoutHistory>() {
    override fun areItemsTheSame(
        oldItem: WorkoutHistory,
        newItem: WorkoutHistory
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WorkoutHistory,
        newItem: WorkoutHistory
    ): Boolean {
        return oldItem == newItem
    }
}