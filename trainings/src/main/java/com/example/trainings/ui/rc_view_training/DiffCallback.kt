package com.example.trainings.ui.rc_view_training

import androidx.recyclerview.widget.DiffUtil
import com.example.trainings.data.response.ExerciseUi

class DiffCallback : DiffUtil.ItemCallback<ExerciseUi>() {
    override fun areItemsTheSame(oldItem: ExerciseUi, newItem: ExerciseUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExerciseUi, newItem: ExerciseUi): Boolean {
        return oldItem == newItem
    }
}