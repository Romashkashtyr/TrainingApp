package com.example.trainingapp.presentation.base.main_logic.rc_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingapp.databinding.ItemStepsBinding
import com.example.trainingapp.databinding.ItemTrainingListBinding
import com.example.trainingapp.databinding.ItemWaterBinding
import com.example.trainingapp.databinding.ItemWorkoutBinding
import com.example.trainingapp.domain.DashboardItem

sealed class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: DashboardItem)

     class StepsViewHolder(private val binding: ItemStepsBinding) : DashboardViewHolder(binding.root) {
        override fun bind(item: DashboardItem){
            binding.stepsCount.text = (item as DashboardItem.StepsItem).stepsCount.toString()
        }
    }

    class WaterViewHolder(
        private val binding: ItemWaterBinding,
        private val onAddWaterClicked: () -> Unit
    ) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.waterIntake.text = (item as DashboardItem.WaterItem).waterCount.toString()
            binding.addWaterButton.setOnClickListener { onAddWaterClicked() }
        }
    }

     class WorkoutsViewHolder(private val binding: ItemWorkoutBinding) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.workoutsCount.text = (item as DashboardItem.WorkoutItem).workoutCounts.toString()
        }
    }

     class TrainingListViewHolder(
        private val binding: ItemTrainingListBinding,
        private val onViewTrainingsClicked: () -> Unit
    ) : DashboardViewHolder(binding.root){
        override fun bind(item: DashboardItem){
            binding.viewWorkoutsButton.text = (item as DashboardItem.TrainingListItem).trainingList
            binding.viewWorkoutsButton.setOnClickListener { onViewTrainingsClicked() }
        }
    }
}