package com.example.main.ui.steps_rc_view

import androidx.recyclerview.widget.DiffUtil
import com.example.main.data.entitieModules.Steps
import com.example.main.data.entitieModules.Water

class StepsDiffCallback: DiffUtil.ItemCallback<Steps>() {
    override fun areItemsTheSame(
        oldItem: Steps,
        newItem: Steps
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: Steps,
        newItem: Steps
    ): Boolean {
        return oldItem == newItem
    }
}