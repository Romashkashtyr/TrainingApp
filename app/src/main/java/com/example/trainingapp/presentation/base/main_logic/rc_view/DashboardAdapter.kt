package com.example.trainingapp.presentation.base.main_logic.rc_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.trainingapp.R
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem

class DashboardAdapter(
    private val onAddWaterClicked: () -> Unit,
    private val onViewTrainingsClicked: () -> Unit
) : ListAdapter<DashboardItem, DashboardViewHolder>(DashboardDiffCallback()) {

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is DashboardItem.StepsItem -> R.layout.item_steps
        is DashboardItem.WaterItem -> R.layout.item_water
        is DashboardItem.WorkoutItem -> R.layout.item_workout
        is DashboardItem.TrainingListItem -> R.layout.item_training_list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.item_steps -> DashboardViewHolder.StepsViewHolder(
                ItemStepsBinding.inflate(inflater, parent, false)
            )
            R.layout.item_water -> DashboardViewHolder.WaterViewHolder(
                ItemWaterBinding.inflate(inflater, parent, false),
                onAddWaterClicked
            )
            R.layout.item_workout -> DashboardViewHolder.WorkoutsViewHolder(
                ItemWorkoutBinding.inflate(inflater, parent, false)
            )
            R.layout.item_training_list -> DashboardViewHolder.TrainingListViewHolder(
                ItemTrainingListBinding.inflate(inflater, parent, false),
                onViewTrainingsClicked
            )
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is DashboardItem.StepsItem -> (holder as DashboardViewHolder.StepsViewHolder).bind(item)
            is DashboardItem.WaterItem -> (holder as DashboardViewHolder.WaterViewHolder).bind(item)
            is DashboardItem.WorkoutItem -> (holder as DashboardViewHolder.WorkoutsViewHolder).bind(item)
            is DashboardItem.TrainingListItem -> (holder as DashboardViewHolder.TrainingListViewHolder).bind(item)
        }
    }

    private class DashboardDiffCallback : DiffUtil.ItemCallback<DashboardItem>() {
        override fun areItemsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean =
            oldItem::class == newItem::class

        override fun areContentsTheSame(oldItem: DashboardItem, newItem: DashboardItem): Boolean =
            oldItem == newItem
    }
}