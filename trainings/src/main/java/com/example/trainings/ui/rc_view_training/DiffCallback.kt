package com.example.trainings.ui.rc_view_training

import androidx.recyclerview.widget.DiffUtil
import com.example.trainings.data.response.FullExercise

class DiffCallback : DiffUtil.ItemCallback<FullExercise>() {
    override fun areItemsTheSame(oldItem: FullExercise, newItem: FullExercise): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FullExercise, newItem: FullExercise): Boolean {
        return oldItem == newItem
    }
}